package use_case.setting;

import app.*;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import data_access.ChannelDataAccessObject;
import entity.Channel;
import entity.Message;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChannelHistoryTest {

    private ViewManagerModel viewManagerModel;
    private SettingView settingView;
    private SettingViewModel settingViewModel;
    private ChannelHistoryViewModel channelHistoryViewModel;
    private ChannelHistoryView channelHistoryView;
    private User user;
    private PubNub pubnub;
    private Channel channel;
    private String msg;
    private ArrayList<Message> messageLog;
    private SubscribeCallback listener;


    @BeforeEach
    public void setup() throws PubNubException {
        JFrame application = new JFrame("Chat App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initialize all view models.
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        CreateRoomViewModel createRoomViewModel = new CreateRoomViewModel();
        SubscribeRoomViewModel subscribeRoomViewModel = new SubscribeRoomViewModel();
        RoomViewModel roomViewModel = new RoomViewModel();
        JournalViewModel journalViewModel = new JournalViewModel();
        channelHistoryViewModel = new ChannelHistoryViewModel();
        settingViewModel = new SettingViewModel();

        ChannelDataAccessObject channelDataAccessObject;
        try {
            channelDataAccessObject = new ChannelDataAccessObject("./data/channels.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                subscribeRoomViewModel, loginViewModel, profileViewModel, channelDataAccessObject);
        RoomView roomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel,
                journalViewModel, settingViewModel);
        JournalView journalView = JournalUsecaseFactory.create(viewManagerModel, journalViewModel, roomViewModel);
        settingView = ChannelSettingUseCaseFactory.create(viewManagerModel, settingViewModel,
                channelHistoryViewModel, roomViewModel);
        channelHistoryView = ChannelHistoryUseCaseFactory.create(viewManagerModel, settingViewModel, channelHistoryViewModel);

        views.add(profileView, profileView.viewName);
        views.add(roomView, roomView.viewName);
        views.add(journalView, journalView.viewName);
        views.add(settingView, settingView.viewName);

        viewManagerModel.setActiveView(roomView.viewName);
        cardLayout.show(views, viewManagerModel.getActiveView());
        application.pack();
        application.setVisible(true);

        user = new User("user1", "password1");
        channel = new Channel("SettingTest", user);

        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        pubnub = new PubNub(pnConfiguration);

        RoomState state = roomViewModel.getState();
        state.setUser(user);
        state.setChannel(channel);
        state.setConfig(pubnub);
        state.setListener(listener);
        state.setMessage(msg);
        state.setMessageLog(messageLog);
        roomViewModel.setState(state);

        ChannelHistoryState currState = channelHistoryViewModel.getState();
        currState.setCurrentUser(user);
        currState.setChannel(channel.getName());
        currState.setConfig(pubnub);
        channelHistoryViewModel.setState(currState);
    }

    @Test
    public void TestShowChannelHistoryInteractor () {
        ArrayList<Message> msgs = LoadHistory(channel.getName());

        int originalLength = msgs.size();

        ChannelHistoryState state = channelHistoryViewModel.getState();
        state.setActive(true);
        channelHistoryViewModel.setState(state);
        channelHistoryViewModel.firePropertyChanged();

        assertEquals(state.getChannelMessages().size(), originalLength);
        assertEquals(channelHistoryViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void TestReturnToSettingInteractor (){
        channelHistoryView.simulateCancelButtonPress();

        assertEquals(settingViewModel.getViewName(), viewManagerModel.getActiveView());
    }


    @Test
    public void TestDeleteMessage () {
        ArrayList<Message> msgs = LoadHistory(channel.getName());

        int originalLength = msgs.size();

        channelHistoryView.simulateDeleteButtonsPress();

        ArrayList<Message> msgsNew = LoadHistory(channel.getName());

        int newLength = msgsNew.size();

        assert newLength != originalLength;
    }


    public ArrayList<Message> LoadHistory(String channelName) {

        ArrayList<Message> messages = new ArrayList<>();

        pubnub.fetchMessages()
                .channels(Collections.singletonList(channel.getName()))
                .maximumPerChannel(100)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(@Nullable PNFetchMessagesResult pnFetchMessagesResult, @NotNull PNStatus pnStatus) {
                        if (!pnStatus.isError()) {
                            List<PNFetchMessageItem> channelMessages = pnFetchMessagesResult.getChannels().get(channelName);

                            if (channelMessages != null) {
                                for (PNFetchMessageItem messageItem : channelMessages) {
                                    String username = messageItem.getUuid();
                                    String content = messageItem.getMessage().getAsJsonObject().get("msg").toString();

                                    // remove quotation marks
                                    content = content.substring(1, content.length() - 1);
                                    messages.add(new Message(new User(username), content, messageItem.getTimetoken()));
                                }
                            }
                        }

                    }
                });

        return messages;

    }

}

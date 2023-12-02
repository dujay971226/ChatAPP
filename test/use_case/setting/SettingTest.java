package use_case.setting;

import app.ChannelSettingUseCaseFactory;
import app.JournalUsecaseFactory;
import app.ProfileUseCaseFactory;
import app.RoomUseCaseFactory;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.SubscribeCallback;
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
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showsetting.SettingState;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingTest {

    private ViewManagerModel viewManagerModel;
    private SettingView settingView;
    private SettingViewModel settingViewModel;
    private RoomView roomView;
    private RoomViewModel roomViewModel;
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
        roomViewModel = new RoomViewModel();
        JournalViewModel journalViewModel = new JournalViewModel();
        ChannelHistoryViewModel channelHistoryViewModel = new ChannelHistoryViewModel();
        settingViewModel = new SettingViewModel();

        ChannelDataAccessObject channelDataAccessObject;
        try {
            channelDataAccessObject = new ChannelDataAccessObject("./data/channels.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                subscribeRoomViewModel, loginViewModel, profileViewModel, channelDataAccessObject);
        roomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel,
                journalViewModel, settingViewModel);
        JournalView journalView = JournalUsecaseFactory.create(viewManagerModel, journalViewModel, roomViewModel);
        settingView = ChannelSettingUseCaseFactory.create(viewManagerModel, settingViewModel,
                channelHistoryViewModel, roomViewModel);

        views.add(profileView, profileView.viewName);
        views.add(roomView, roomView.viewName);
        views.add(journalView, journalView.viewName);
        views.add(settingView, settingView.viewName);

        viewManagerModel.setActiveView(roomView.viewName);
        cardLayout.show(views, viewManagerModel.getActiveView());
        application.pack();
        application.setVisible(true);

        user = new User("user1", "password1");
        channel = new Channel("RoomTest", user);

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

        SettingState currState = settingViewModel.getState();
        currState.setUser(user);
        currState.setChannel(channel);
        currState.setConfig(pubnub);
        settingViewModel.setState(currState);

        viewManagerModel.setActiveView(settingView.viewName);
        viewManagerModel.firePropertyChanged();
    }

    @Test
    public void TestChannelSettingLoadingData () {
        SettingState state = settingViewModel.getState();
        state.setActiveState(true);
        settingViewModel.setState(state);
        settingViewModel.firePropertyChanged();
        settingViewModel.setState(state);
        settingViewModel.firePropertyChanged();

        assertEquals(settingViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void TestChannelSettingLoading () {
        SettingState state = settingViewModel.getState();
        settingViewModel.setState(state);
        settingViewModel.firePropertyChanged();

        assertEquals(settingViewModel.getViewName(), viewManagerModel.getActiveView());
    }


    @Test
    public void TestReturnToChannel () {
        settingView.simulateCancelButtonPress();
        assertEquals(roomViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void TestSettingToChannelHistory () {
        settingView.simulateChannelHistoryButtonPress();

        assertEquals("channel history", viewManagerModel.getActiveView());
    }
}

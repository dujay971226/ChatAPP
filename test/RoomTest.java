import app.ChannelSettingUseCaseFactory;
import app.JournalUsecaseFactory;
import app.ProfileUseCaseFactory;
import app.RoomUseCaseFactory;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    private ViewManagerModel viewManagerModel;
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
        SettingViewModel settingViewModel = new SettingViewModel();

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
        SettingView settingView = ChannelSettingUseCaseFactory.create(viewManagerModel, settingViewModel,
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
        msg = "Message Test";
        Message message1 = new Message(user, "history1", LocalDateTime.now());
        Message message2 = new Message(user, "history2", LocalDateTime.now());
        Message message3 = new Message(user, "history3", LocalDateTime.now());
        messageLog = new ArrayList<Message>();
        messageLog.add(message1);
        messageLog.add(message2);
        messageLog.add(message3);

        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        pubnub = new PubNub(pnConfiguration);
        listener = new SubscribeCallback() {

            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {

                } else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {

                } else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
                    // Happens as part of our regular operation. This event happens when
                    // radio / connectivity is lost, then regained.
                } else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
                    // Handle message decryption error. Probably client configured to
                    // encrypt messages and on live data feed it received plain text.
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                // Handle new message stored in message.message
                /*
                 * Log the following items with your favorite logger - message.getMessage() -
                 * message.getSubscription() - message.getTimetoken()
                 */
            }

            @Override
            public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {

            }

            @Override
            public void uuid(PubNub pubnub, PNUUIDMetadataResult pnUUIDMetadataResult) {

            }

            @Override
            public void channel(@NotNull PubNub pubNub, @NotNull PNChannelMetadataResult pnChannelMetadataResult) {

            }

            @Override
            public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {

            }

            @Override
            public void messageAction(PubNub pubnub, PNMessageActionResult pnMessageActionResult) {

            }

            @Override
            public void file(@NotNull PubNub pubNub, @NotNull PNFileEventResult pnFileEventResult) {

            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        };

        RoomState currState = roomViewModel.getState();
        currState.setUser(user);
        currState.setChannel(channel);
        currState.setConfig(pubnub);
        currState.setListener(listener);
        currState.setMessage(msg);
        currState.setMessageLog(messageLog);
        roomViewModel.setState(currState);
        roomViewModel.firePropertyChanged();

    }


    @Test
    public void TestLoadMessageLog () {

        int originalSize = roomView.getListModelSize();

        RoomState currState = roomViewModel.getState();

        currState.setMessageLog(messageLog);
        currState.setNotice();
        roomViewModel.setState(currState);
        roomViewModel.firePropertyChanged();

        int updatedSize = roomView.getListModelSize();

        assertEquals(originalSize + 3, updatedSize);

    }


    @Test
    public void TestSendButton () {

        ArrayList<Message> msgs = LoadHistory(channel.getName());

        int originalLength = msgs.size();

        roomView.simulateSend();

        ArrayList<Message> newMsgs = LoadHistory(channel.getName());

        int updatedLength = newMsgs.size();

        assertEquals(originalLength, updatedLength);

    }

    @Test
    public void TestEnterPressed () throws AWTException {

        ArrayList<Message> msgs = LoadHistory(channel.getName());

        int originalLength = msgs.size();

        roomView.simulateEnter();

        ArrayList<Message> newMsgs = LoadHistory(channel.getName());

        int updatedLength = newMsgs.size();

        assertEquals(originalLength, updatedLength);

    }


    @Test
    public void TestReceiveMessage () throws InterruptedException {

        String receivedMessage = roomView.simulateReceive();

        assertEquals("Hello New World", receivedMessage);

    }


    @Test
    public void TestExit () {

        roomView.simulateExitButtonPress();

        assertEquals("profile", viewManagerModel.getActiveView());

    }


    @Test
    public void TestToJournal () {

        roomView.simulateJournalButtonPress();

        assertEquals("journal", viewManagerModel.getActiveView());

    }

    @Test
    public void TestToSetting () {

        roomView.simulateSettingButtonPress();

        assertEquals("channel setting", viewManagerModel.getActiveView());

    }


    public ArrayList<Message> LoadHistory(String channelName) {

        ArrayList<Message> msgs = new ArrayList<>();

        pubnub.fetchMessages()
                .channels(Collections.singletonList(channel.getName()))
                .maximumPerChannel(25)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(@Nullable PNFetchMessagesResult pnFetchMessagesResult, @NotNull PNStatus pnStatus) {
                        if (!pnStatus.isError()) {
                            Map<String, List<PNFetchMessageItem>> channels = pnFetchMessagesResult.getChannels();
                            for (PNFetchMessageItem messageItem : channels.get(channelName)) {
                                long time = messageItem.getTimetoken() / 10000000L;
                                LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                        TimeZone.getDefault().toZoneId());
                                String mesString = messageItem.getMessage().getAsJsonObject().
                                        getAsJsonPrimitive("msg").toString();
                                mesString = mesString.substring(1, mesString.length() - 1); // remove quotation marks
                                Message mes = new Message(new User(messageItem.getUuid()), mesString,
                                        localDateTime);
                                msgs.add(mes);
                            }
                        }

                    }
                });

        return msgs;

    }

}

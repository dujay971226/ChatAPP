import app.*;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import data_access.ChannelDataAccessObject;
import data_access.UserDataAccessObject;
import entity.Channel;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for subscribe room view.
 */
class SubscribeRoomViewTest {

    ViewManagerModel viewManagerModel;
    SubscribeRoomViewModel subscribeRoomViewModel;
    SubscribeRoomView subscribeRoomView;
    PubNub pubNub;
    ChannelDataAccessObject channelDataAccessObject;

    /**
     * Initializes test.
     * @throws PubNubException
     * @throws IOException
     */
    @BeforeEach
    void testInit() throws PubNubException, IOException {
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
        subscribeRoomViewModel = new SubscribeRoomViewModel();
        RoomViewModel roomViewModel = new RoomViewModel();
        JournalViewModel journalViewModel = new JournalViewModel();
        ChannelHistoryViewModel channelHistoryViewModel = new ChannelHistoryViewModel();
        SettingViewModel settingViewModel = new SettingViewModel();

        // Initialize all data access objects.
        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./data/users.csv", new UserFactory());
            channelDataAccessObject = new ChannelDataAccessObject("./test/channel_test.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PNConfiguration pnConfiguration = new PNConfiguration(new UserId("testing user"));
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        pubNub = new PubNub(pnConfiguration);
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        state.setConfig(pubNub);
        state.setUser(new User("testing user", "testing password"));
        subscribeRoomViewModel.setState(state);

        // Initialize all views.
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, profileViewModel,
                signupViewModel, userDataAccessObject);
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                subscribeRoomViewModel, loginViewModel, profileViewModel, channelDataAccessObject);
        CreateRoomView createRoomView = CreateRoomUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                roomViewModel, subscribeRoomViewModel, profileViewModel, journalViewModel, settingViewModel,
                channelDataAccessObject);
        subscribeRoomView = SubscribeRoomUseCaseFactory.create(viewManagerModel,
                subscribeRoomViewModel, roomViewModel, createRoomViewModel, profileViewModel, journalViewModel,
                settingViewModel, channelDataAccessObject);
        RoomView roomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel,
                journalViewModel, settingViewModel);
        JournalView journalView = JournalUsecaseFactory.create(viewManagerModel, journalViewModel, roomViewModel);
        ChannelHistoryView channelHistoryView = ChannelHistoryUseCaseFactory.create(viewManagerModel,
                settingViewModel, channelHistoryViewModel);
        SettingView settingView = ChannelSettingUseCaseFactory.create(viewManagerModel, settingViewModel,
                channelHistoryViewModel, roomViewModel);

        // Add views to views.
        views.add(loginView, loginView.viewName);
        views.add(signupView, signupView.viewName);
        views.add(profileView, profileView.viewName);
        views.add(createRoomView, createRoomView.viewName);
        views.add(subscribeRoomView, subscribeRoomView.viewName);
        views.add(roomView, roomView.viewName);
        views.add(journalView, journalView.viewName);
        views.add(channelHistoryView, channelHistoryView.viewName);
        views.add(settingView, settingView.viewName);

        viewManagerModel.setActiveView(subscribeRoomView.viewName);
        cardLayout.show(views, viewManagerModel.getActiveView());
    }

    /**
     * Tests subscribe button empty string.
     */
    @org.junit.jupiter.api.Test
    void testSubscribeButtonEmptyString() {
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        state.setChannelName("");
        subscribeRoomViewModel.setState(state);
        subscribeRoomView.simulateSubscribeButton();
        assertEquals("", subscribeRoomViewModel.getState().getChannelName());
    }

    /**
     * Tests subscribe button with nonempty string.
     */
    @org.junit.jupiter.api.Test
    void testSubscribeButtonNotEmptyString() {
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        channelDataAccessObject.save("test channel", state.getUser());
        String channelName = "Test";
        state.setChannelName(channelName);
        subscribeRoomViewModel.setState(state);
        subscribeRoomView.simulateSubscribeButton();
        assertEquals(channelName, state.getChannelName());
    }

    /**
     * Tests with nonempty channel log.
     */
    @org.junit.jupiter.api.Test
    void testNonEmptyChannelLog() {
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        String channelName = "testing channel";
        state.setChannelName("testing channel");
        ArrayList<Channel> channelLog = new ArrayList<Channel>();
        channelLog.add(new Channel("c1", state.getUser()));
        channelLog.add(new Channel("c2", state.getUser()));
        state.setChannelLog(channelLog);
        subscribeRoomViewModel.setState(state);
        pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();
        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", "testing message");
        pubNub.publish()
                .channel(channelName)
                .message(messageJsonObject)
                .async((result, publishStatus) -> {
                            if (publishStatus.isError()) {throw new RuntimeException("Publish status error."); }
                        }
                );
        subscribeRoomView.simulateSubscribeButton();
        assertEquals("testing channel", subscribeRoomViewModel.getState().getChannelName());
    }

    /**
     * Tests to create button.
     */
    @org.junit.jupiter.api.Test
    void testToCreateButton() {
        subscribeRoomView.simulateCreateButton();
        assertEquals("create", viewManagerModel.getActiveView());
    }

    /**
     * Tests property change.
     */
    @org.junit.jupiter.api.Test
    void testPropertyChange() {
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        state.setChannelName("new channel");
        ArrayList<Channel> channelLog = new ArrayList<Channel>();
        channelLog.add(new Channel("c1", state.getUser()));
        channelLog.add(new Channel("c2", state.getUser()));
        state.setChannelLog(channelLog);
        subscribeRoomViewModel.firePropertyChanged();
        assertEquals("new channel", subscribeRoomViewModel.getState().getChannelName());
    }

}
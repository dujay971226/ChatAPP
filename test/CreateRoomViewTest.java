import app.*;
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
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for create room view.
 */
class CreateRoomViewTest {

    private ViewManagerModel viewManagerModel;
    private CreateRoomView createRoomView;
    private CreateRoomViewModel createRoomViewModel;


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
        createRoomViewModel = new CreateRoomViewModel();
        SubscribeRoomViewModel subscribeRoomViewModel = new SubscribeRoomViewModel();
        RoomViewModel roomViewModel = new RoomViewModel();
        JournalViewModel journalViewModel = new JournalViewModel();
        ChannelHistoryViewModel channelHistoryViewModel = new ChannelHistoryViewModel();
        SettingViewModel settingViewModel = new SettingViewModel();

        // Initialize all data access objects.
        UserDataAccessObject userDataAccessObject;
        ChannelDataAccessObject channelDataAccessObject;
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
        PubNub pubNub = new PubNub(pnConfiguration);
        CreateRoomState state = createRoomViewModel.getState();
        state.setConfig(pubNub);
        state.setUser(new User("testing user", "testing password"));
        state.setChannelName("testing channel name");
        createRoomViewModel.setState(state);

        // Initialize all views.
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, profileViewModel,
                signupViewModel, userDataAccessObject);
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                subscribeRoomViewModel, loginViewModel, profileViewModel, channelDataAccessObject);
        createRoomView = CreateRoomUseCaseFactory.create(viewManagerModel, createRoomViewModel,
                roomViewModel, subscribeRoomViewModel, profileViewModel, journalViewModel, settingViewModel,
                channelDataAccessObject);
        SubscribeRoomView subscribeRoomView = SubscribeRoomUseCaseFactory.create(viewManagerModel,
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

        viewManagerModel.setActiveView(createRoomView.viewName);
        cardLayout.show(views, viewManagerModel.getActiveView());
    }

    /**
     * Tests create button.
     * @throws PubNubException
     * @throws IOException
     */
    @org.junit.jupiter.api.Test
    void testCreateButton() throws PubNubException, IOException {
        createRoomView.simulateCreateButton();
        assertEquals("room", viewManagerModel.getActiveView());
    }

    /**
     * Tests join button.
     */
    @org.junit.jupiter.api.Test
    void testJoinButton() {
        createRoomView.simulateJoinButton();
        assertEquals("subscribe", viewManagerModel.getActiveView());
    }

    /**
     * Tests fire property change.
     */
    @org.junit.jupiter.api.Test
    void testFirePropertyChange() {
        CreateRoomState state = createRoomViewModel.getState();
        state.setChannelName("new testing channel name");
        state.setUser(new User("new user", "new password"));
        createRoomViewModel.firePropertyChanged();
        assertEquals(state, createRoomViewModel.getState());
    }

    /**
     * Tests channel name error.
     */
    @org.junit.jupiter.api.Test
    void testChannelNameError() {
        CreateRoomState state = createRoomViewModel.getState();
        state.setChannelNameError("Testing Error");
        createRoomViewModel.firePropertyChanged();
        assertNotNull(createRoomViewModel.getState().getChannelNameError());
    }





}
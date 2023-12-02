package app;


import com.pubnub.api.PubNubException;
import data_access.ChannelDataAccessObject;
import data_access.UserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Main class for running the application.
 *
 * @author huangzhihao
 */
public class Main {

    /**
     * Run application using SwingUtilities.invokeLater.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowApplication();
                } catch (PubNubException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static void createAndShowApplication() throws PubNubException {
        JFrame application = new JFrame("Chat App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initialize all view models.
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        CreateRoomViewModel createRoomViewModel = new CreateRoomViewModel();
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
            channelDataAccessObject = new ChannelDataAccessObject("./data/channels.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        viewManagerModel.setActiveView(loginView.viewName);
        cardLayout.show(views, viewManagerModel.getActiveView());
        application.pack();
        application.setVisible(true);
    }
}

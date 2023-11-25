package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.signup.SignupViewModel;

import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import okhttp3.internal.http2.Settings;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for running the application.
 * @author huangzhihao
 */
public class Main {

    /**
     * Run application using SwingUtilities.invokeLater.
     * @param args not used
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowApplication();
            }
        });
    }

    private static void createAndShowApplication() {
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

        // Initialize all views and add to views.
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel);
        SignupView signupView = SignupUseCaseFactory.create();
        ProfileView profileView = ProfileRoomUseCaseFactory.create();
        CreateRoomView createRoomView = CreateRoomUseCaseFactory.create();
        SubscribeRoomView subscribeRoomView = SubscribeRoomUseCaseFactory.create();
        RoomView roomView = RoomViewUseCaseFactory.create();
        JournalView journalView = JournalUsecaseFactory.create();
        ChannelHistoryView channelHistoryView = ChannelHistoryUseCaseFactory.create();
        SettingView settingView = SettingUseCaseFactory.create();

        application.pack();
        application.setVisible(true);
    }
}

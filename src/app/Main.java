package app;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.User;
import interface_adapter.*;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import interface_adapter.showsetting.SettingViewModel;
import interface_adapter.signup.SignupViewModel;
import view.RoomView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws PubNubException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Chat App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        RoomViewModel roomViewModel = new RoomViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        JournalViewModel journalViewModel = new JournalViewModel();
        SettingViewModel settingViewModel = new SettingViewModel();

        RoomState roomState = roomViewModel.getState();
        User user = new User("Jay", "asdf");
        Channel channel = new Channel("chtest", user);
        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration =  new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        PubNub pubnub = new PubNub(pnConfiguration);
        roomState.setUser(user);
        roomState.setChannel(channel);
        roomState.setConfig(pubnub);
        roomViewModel.setState(roomState);



        RoomView roomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, profileViewModel, journalViewModel, settingViewModel);
        views.add(roomView, roomView.viewName);

//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = new LoginView(loginViewModel);
//        views.add(loginView, loginView.viewName);
//
//        PorfolioView porfolioView = PorfolioUseCaseFactory.create();
//        views.add(porfolioView, porfolioView.viewName);
//
//        ChannelView channelView = ChannelUseCaseFactory.create();
//        views.add(channelView, channelView.viewName);
//
//        NewChannelView newChannelView = ChannelUseCaseFactory.create();
//        views.add(newChannelView, newChannelView.viewName);
//
        viewManagerModel.setActiveView(roomView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
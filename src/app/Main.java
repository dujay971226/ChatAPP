package app;

import interface_adapter.room.RoomViewModel;
import interface_adapter.ViewManagerModel;
import view.RoomView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Chat App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

       // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);
//
//        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        RoomView roomView = RoomUseCaseFactory.create(viewManagerModel, roomViewModel, );


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
        RoomView roomView = RoomUseCaseFactory.create();
//        views.add(newChannelView, newChannelView.viewName);
//
//        viewManagerModel.setActiveView(loginView.viewName);
//        viewManagerModel.firePropertyChanged();

//        application.pack();
//        application.setVisible(true);
    }
}
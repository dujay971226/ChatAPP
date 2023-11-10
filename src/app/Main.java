package app;

import interface_adapter.Profile.ProfileController;
import interface_adapter.Profile.ProfileViewModel;
import view.ProfileView;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){
        JFrame application = new JFrame("profile example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ProfileViewModel profileViewModel= new ProfileViewModel();
        ProfileView profileView = new ProfileView(profileViewModel);
        views.add(profileView,profileView.viewName);

        application.pack();
        application.setVisible(true);
    }
}
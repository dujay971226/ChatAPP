package app;

import entity.User;
import interface_adapter.Profile.ProfileViewModel;
import view.ProfileView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){
        JFrame application = new JFrame("profile example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ProfileViewModel profileViewModel= new ProfileViewModel();
        User user1 = new User("user1","112233","00");
        ProfileView profileView = new ProfileView(profileViewModel,user1);
        views.add(profileView,profileView.viewName);

        application.pack();
        application.setVisible(true);

    }


}

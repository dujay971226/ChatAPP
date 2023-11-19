package app;

import entity.User;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
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
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        User user1 = new User("user1",null,"00");
        ProfileView profileView = ProfileUsecaseFactory.create(viewManagerModel,null,null,profileViewModel,null);
        views.add(profileView,profileView.viewName);

        application.pack();
        application.setVisible(true);

    }


}

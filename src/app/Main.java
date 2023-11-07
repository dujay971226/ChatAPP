package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import view.ViewManager;
import view.create_room.CreateRoomView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Create or Subscribe");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);
        CreateRoomViewModel model = new CreateRoomViewModel();
        CreateRoomView view = new CreateRoomView(model);
        panel.add(view);
        application.add(panel);
        application.pack();
        application.setVisible(true);
    }


}

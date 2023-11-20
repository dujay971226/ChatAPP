package app;

import entity.User;
import interface_adapter.Journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import view.JournalView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){
        JFrame application = new JFrame("profile example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        JournalViewModel journalViewModel = new JournalViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        JournalView journalView = JournalUsecaseFactory.create(viewManagerModel,journalViewModel);
        views.add(journalView,journalView.viewName);

        application.pack();
        application.setVisible(true);

    }


}

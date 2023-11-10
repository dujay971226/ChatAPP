package view;

import entity.User;
import interface_adapter.Profile.ProfileController;
import interface_adapter.Profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "sign up";
    private final ProfileViewModel profileViewModel;
    private final JTextField channelidinputField = new JTextField(15);
        //private final ProfileController profileController;

    private final JButton createchannel;
    private final JButton subscribechannel;
    private final JButton logout;

    public ProfileView(ProfileViewModel profileViewModel, User user){




        this.profileViewModel = profileViewModel;
        //this.profileController = controller;
        profileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ProfileViewModel.TITLE);
        title.setText(user.getname() + ProfileViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();

        JLabel channelidinputlabel = new JLabel("Channel ID");
        JPanel channelidinputinfo = new JPanel();

        channelidinputinfo.add(channelidinputlabel);
        channelidinputinfo.add(channelidinputField);

        createchannel = new JButton((ProfileViewModel.CREATE_BUTTON_LABEL));
        subscribechannel = new JButton(ProfileViewModel.SUBSRIBE_BUTTON_LABEL);
        logout = new JButton(ProfileViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(createchannel);
        buttons.add(subscribechannel);
        buttons.add(logout);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel userid = new JLabel("userid");
        userid.setText("ID:" + user.getuserid());
        userid.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.add(title);
        this.add(userid);
        this.add(channelidinputinfo);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

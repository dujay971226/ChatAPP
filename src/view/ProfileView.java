package view;

import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;

import interface_adapter.profile.profiletologout.ProfileToLogoutController;

import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final JButton createchannel;
    private final JButton subscribechannel;
    private final JButton logout;

    private final ProfileToCreateController profileToCreateController;
    private final ProfileToSubscribeController profileToSubscribeController;
    private final ProfileToLogoutController profileToLogoutController;


    public ProfileView(ProfileViewModel profileViewModel, ProfileToCreateController profileToCreateController, ProfileToSubscribeController profileToSubscribeController, ProfileToLogoutController profileToLogoutController){

        this.profileViewModel = profileViewModel;
        this.profileToCreateController = profileToCreateController;
        this.profileToSubscribeController = profileToSubscribeController;
        this.profileToLogoutController = profileToLogoutController;


        profileViewModel.addPropertyChangeListener(this);
        ProfileState profileState = profileViewModel.getState();
        JLabel title = new JLabel(ProfileViewModel.TITLE);
        title.setText(profileState.getUser().getName() + ProfileViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();


        createchannel = new JButton((ProfileViewModel.CREATE_BUTTON_LABEL));
        //listener
        createchannel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(createchannel)){
                            ProfileState currentState = profileViewModel.getState();
                            profileToCreateController.execute(currentState.getUser(),currentState.getConfig());
                        }
                    }
                }
        );
        subscribechannel = new JButton(ProfileViewModel.SUBSRIBE_BUTTON_LABEL);
        subscribechannel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(subscribechannel)){
                            ProfileState currentState = profileViewModel.getState();
                            profileToSubscribeController.execute(currentState.getUser(),currentState.getConfig());
                        }
                    }
                }
        );
        logout = new JButton(ProfileViewModel.LOGOUT_BUTTON_LABEL);
        logout.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(logout)){
                            profileToLogoutController.execute();
                        }
                    }
                }
        );

        buttons.add(createchannel);
        buttons.add(subscribechannel);
        buttons.add(logout);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));





        this.add(title);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

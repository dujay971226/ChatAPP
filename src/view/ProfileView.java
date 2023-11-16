package view;

import interface_adapter.Profile.profiletocreate.ProfiletocreateController;
import interface_adapter.Profile.ProfileState;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Profile.profiletosubscribe.ProfiletosubscribeController;

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

    private final ProfiletocreateController profiletocreateController;
    private final ProfiletosubscribeController profiletosubscribeController;


    public ProfileView(ProfileViewModel profileViewModel,ProfiletocreateController profiletocreateController, ProfiletosubscribeController profiletosubscribeController){

        this.profileViewModel = profileViewModel;
        this.profiletocreateController = profiletocreateController;
        this.profiletosubscribeController = profiletosubscribeController;


        profileViewModel.addPropertyChangeListener(this);
        ProfileState profileState = profileViewModel.getState();
        JLabel title = new JLabel(ProfileViewModel.TITLE);
        title.setText(profileState.getUser().getname() + ProfileViewModel.TITLE);
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
                            profiletocreateController.execute(currentState.getUser(),currentState.getConfig());
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
                            profiletosubscribeController.execute(currentState.getUser(),currentState.getConfig());
                        }
                    }
                }
        );
        logout = new JButton(ProfileViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(createchannel);
        buttons.add(subscribechannel);
        buttons.add(logout);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel userid = new JLabel("userid");
        userid.setText("ID:" + profileViewModel.getState().getUser().getuserid().toString());
        userid.setAlignmentX(Component.CENTER_ALIGNMENT);




        this.add(title);
        this.add(userid);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

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

/**
 * The ProfileView class represents the user interface for the profile-related functionality.
 * It allows users to create channels, subscribe to channels, and logout from their profile.
 * This view includes buttons and a title label to display the user's profile information.
 *
 * @author Xiaofeng Li
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final JButton createchannel;
    private final JButton subscribechannel;
    private final JButton logout;

    private final ProfileToCreateController profileToCreateController;
    private final ProfileToSubscribeController profileToSubscribeController;
    private final ProfileToLogoutController profileToLogoutController;
    private JLabel title;

    /**
     * Constructs a new ProfileView instance.
     *
     * @param profileViewModel       The view model associated with this view.
     * @param profileToCreateController The controller for creating channels.
     * @param profileToSubscribeController The controller for subscribing to channels.
     * @param profileToLogoutController The controller for logging out.
     */
    public ProfileView(ProfileViewModel profileViewModel, ProfileToCreateController profileToCreateController, ProfileToSubscribeController profileToSubscribeController, ProfileToLogoutController profileToLogoutController){

        this.profileViewModel = profileViewModel;
        this.profileToCreateController = profileToCreateController;
        this.profileToSubscribeController = profileToSubscribeController;
        this.profileToLogoutController = profileToLogoutController;
        this.profileViewModel.addPropertyChangeListener(this);

        profileViewModel.addPropertyChangeListener(this);
        ProfileState profileState = profileViewModel.getState();
        title = new JLabel(profileViewModel.getState().getUser().getName() + ProfileViewModel.TITLE);
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

    /**
     * Responds to property change events and updates the title label with the user's profile information.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        title.setText(profileViewModel.getState().getUser().getName() + ProfileViewModel.TITLE);
    }
}

package data_access.Profile;

import entity.User;

import java.beans.PropertyChangeSupport;

public class ProfileViewModel {
    final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ProfileState state;
    public static final String SUBSRIBE_BUTTON_LABEL = "Subscribe";
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String LOGOUT_BUTTON_LABEL = "Logout";
    public ProfileViewModel(ProfileState state){
        this.state = state;
    }
    public ProfileState getState(){
        return this.state;
    }
    public void setState(ProfileState state){
        this.state = state;
    }

}

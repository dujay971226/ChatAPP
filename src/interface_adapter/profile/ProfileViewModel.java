package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ProfileState state = new ProfileState();
    public static final String TITLE = "'s Profile";
    public static final String SUBSRIBE_BUTTON_LABEL = "Subscribe";
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String LOGOUT_BUTTON_LABEL = "Logout";

    public ProfileViewModel() {
        super("profile");
    }

    public ProfileState getState() {
        return this.state;
    }

    public void setState(ProfileState state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}

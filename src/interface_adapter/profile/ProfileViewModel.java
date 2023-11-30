package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the profile view in the application.
 * This class extends ViewModel and manages the state of the profile view, including
 * handling property changes and updating the view based on user profile operations.
 *
 * @author Xiaofeng Li
 */
public class ProfileViewModel extends ViewModel {
    public static final String TITLE = "'s Profile";
    public static final String SUBSRIBE_BUTTON_LABEL = "Subscribe";
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String LOGOUT_BUTTON_LABEL = "Logout";
    final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ProfileState state = new ProfileState();

    /**
     * Constructs a ProfileViewModel with the default view name set to "profile".
     */
    public ProfileViewModel() {
        super("profile");
    }

    /**
     * Retrieves the current state of the profile view.
     *
     * @return The current ProfileState.
     */
    public ProfileState getState() {
        return this.state;
    }

    /**
     * Sets the state of the profile view to the specified ProfileState.
     *
     * @param state The new state to be set for the profile view.
     */
    public void setState(ProfileState state) {
        this.state = state;
    }

    /**
     * Adds a property change listener to this model.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event when the state of the profile view changes.
     * This method notifies all registered listeners of the change.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}

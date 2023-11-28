package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SignupViewModel class extends ViewModel and represents the ViewModel for the signup interface.
 * It contains labels and the state of the signup view, as well as methods for handling property changes.
 */
public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up";
    public static final String USERNAME_LABEL = "Enter Username";
    public static final String PASSWORD_LABEL = "Enter Password";
    public static final String REPEAT_PASSWORD_LABEL = "Repeat Password";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String LOGIN_BUTTON_LABEL = "Login";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    /**
     * Sets the state of the signup view.
     *
     * @param state The new state of the signup view.
     */
    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners about changes in the ViewModel.
     * This is called to alert the View about state changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the signup view.
     *
     * @return The current state of the signup view.
     */
    public SignupState getState() {
        return state;
    }
}

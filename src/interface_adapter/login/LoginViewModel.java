package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The LoginViewModel class extends ViewModel and represents the ViewModel for the login interface.
 * It contains labels and the state of the login view, as well as methods for handling property changes.
 */
public class LoginViewModel extends ViewModel {
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter Username";
    public final String PASSWORD_LABEL = "Enter Password";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("log in");
    }

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
     * Gets the current state of the login view.
     *
     * @return The current state of the login view.
     */
    public LoginState getState() {
        return state;
    }

    /**
     * Sets the state of the login view.
     *
     * @param state The new state of the login view.
     */
    public void setState(LoginState state) {
        this.state = state;
    }
}

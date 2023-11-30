package interface_adapter.login;

/**
 * The LoginState class represents the state of the login interface, including
 * username, password, and related error information.
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * Constructs a LoginState by copying the values from another LoginState instance.
     *
     * @param copy The LoginState to copy values from.
     */
    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    /**
     * Default constructor for LoginState.
     */
    public LoginState() {
    }

    /**
     * Gets the current username in the login state.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username in the login state.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the error related to the username in the login state.
     *
     * @return The username error, or null if no error.
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Sets the username error in the login state.
     *
     * @param usernameError The new username error.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Gets the current password in the login state.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password in the login state.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the error related to the password in the login state.
     *
     * @return The password error, or null if no error.
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the password error in the login state.
     *
     * @param passwordError The new password error.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}

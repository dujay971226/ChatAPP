package interface_adapter.signup;

/**
 * The SignupState class represents the state of the signup interface, including
 * username, password, repeat password, and related error information.
 */
public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    /**
     * Constructs a SignupState by copying the values from another SignupState instance.
     *
     * @param copy The SignupState to copy values from.
     */
    public SignupState(SignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
    }

    public SignupState() {
    }

    /**
     * Gets the current username in the signup state.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the error related to the username in the signup state.
     *
     * @return The username error, or null if no error.
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Gets the current password in the signup state.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the error related to the password in the signup state.
     *
     * @return The password error, or null if no error.
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Gets the current repeat password in the signup state.
     *
     * @return The repeat password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the error related to the repeat password in the signup state.
     *
     * @return The repeat password error, or null if no error.
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Sets the username in the signup state.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error in the signup state.
     *
     * @param usernameError The new username error.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the password in the signup state.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the password error in the signup state.
     *
     * @param passwordError The new password error.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Sets the repeat password in the signup state.
     *
     * @param repeatPassword The new repeat password.
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Sets the repeat password error in the signup state.
     *
     * @param repeatPasswordError The new repeat password error.
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    /**
     * Provides a string representation of the SignupState object.
     *
     * @return A string representation of the SignupState.
     */
    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}

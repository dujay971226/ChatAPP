package use_case.signup;

/**
 * The SignupInputData class encapsulates the input data for the signup use case.
 */
public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;

    /**
     * Constructs SignupInputData with the provided username, password, and repeat password.
     *
     * @param username       The username for the signup.
     * @param password       The password for the signup.
     * @param repeatPassword The repeated password for confirmation.
     */
    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the username associated with the signup.
     *
     * @return The username.
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password associated with the signup.
     *
     * @return The password.
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the repeated password associated with the signup for confirmation.
     *
     * @return The repeated password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}

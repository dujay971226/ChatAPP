package use_case.login;

public class LoginInputData {

    private final String username;
    private final String password;

    /**
     * Constructs LoginInputData with the provided username and password.
     *
     * @param username The username for login.
     * @param password The password for login.
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username from the login input data.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password from the login input data.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
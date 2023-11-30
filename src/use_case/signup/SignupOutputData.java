package use_case.signup;

/**
 * The SignupOutputData class encapsulates the output data for the signup use case.
 */
public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;

    /**
     * Constructs SignupOutputData with the provided username and use case status.
     *
     * @param username      The username associated with the signup.
     * @param useCaseFailed A boolean indicating whether the signup use case failed.
     */
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username associated with the signup.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

}

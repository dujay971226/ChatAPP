package use_case.login;

/**
 * The LoginOutputBoundary interface defines methods for presenting output
 * in the context of the login use case.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the view for a successful login.
     *
     * @param loginOutputData The output data containing information about the login.
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Prepares the view for a failed login.
     *
     * @param error The error message describing the reason for the failure.
     */
    void prepareFailView(String error);

    /**
     * Jumps to the signup view, typically transitioning to another part of the application.
     */
    void jumpSignup();
}
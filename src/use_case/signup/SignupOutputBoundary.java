package use_case.signup;

/**
 * The SignupOutputBoundary interface defines methods for presenting output
 * in the context of the signup use case.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the view for a successful signup.
     *
     * @param user The output data containing information about the signup.
     */
    void prepareSuccessView(SignupOutputData user);

    /**
     * Prepares the view for a failed signup.
     *
     * @param error The error message describing the reason for the failure.
     */
    void prepareFailView(String error);

    /**
     * Jumps to the login view, typically transitioning to another part of the application.
     */
    void jumpLogin();
}

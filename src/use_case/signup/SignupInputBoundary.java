package use_case.signup;

/**
 * The SignupInputBoundary interface defines methods for handling input
 * related to the signup use case.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup use case based on the provided input data.
     *
     * @param signupInputData The input data for the signup.
     */
    void execute(SignupInputData signupInputData);

    /**
     * Jumps to another part of the application or view associated with signup.
     */
    void jump();
}

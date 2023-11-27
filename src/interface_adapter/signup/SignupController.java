package interface_adapter.signup;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * The SignupController class serves as the controller for handling user interactions related to the signup functionality.
 * It interacts with the SignupInputBoundary to execute signup use cases based on user inputs.
 */
public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;

    /**
     * Constructs a SignupController with the provided SignupInputBoundary.
     *
     * @param userSignupUseCaseInteractor The SignupInputBoundary for handling signup use cases.
     */
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the signup use case with the provided username and passwords.
     *
     * @param username  The username entered by the user.
     * @param password1 The first password entered by the user.
     * @param password2 The second password entered by the user for confirmation.
     */
    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Jumps to other views in the signup use case.
     */
    public void jump(){
        userSignupUseCaseInteractor.jump();
    }
}

package interface_adapter.login;

import com.pubnub.api.PubNubException;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The LoginController class serves as the controller for handling user interactions related to the login functionality.
 * It interacts with the LoginInputBoundary to execute login use cases based on user inputs.
 */
public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;

    /**
     * Constructs a LoginController with the provided LoginInputBoundary.
     *
     * @param loginUseCaseInteractor The LoginInputBoundary for handling login use cases.
     */
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the login use case with the provided username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @throws PubNubException If there is an issue with PubNub communication.
     */
    public void execute(String username, String password) throws PubNubException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Jumps to other views in the login use case.
     */
    public void jump(){
        loginUseCaseInteractor.jump();
    }
}

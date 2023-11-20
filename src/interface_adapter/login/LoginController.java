package interface_adapter.login;
import com.pubnub.api.PubNubException;
import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) throws PubNubException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    public void jump(){
        loginUseCaseInteractor.jump();
    }
}
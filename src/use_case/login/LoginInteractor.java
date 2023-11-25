package use_case.login;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import data_access.iUserDataAccessObject;
import entity.User;
import com.pubnub.api.PubNub;

public class LoginInteractor implements LoginInputBoundary{
    final iUserDataAccessObject userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(iUserDataAccessObject userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }
    @Override
    public void execute(LoginInputData loginInputData) throws PubNubException {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());
                UserId userId = new UserId(user.getName());


                PNConfiguration pnConfiguration =  new PNConfiguration(userId);
                pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
                pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd204abc");
                pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");

                PubNub pubnub = new PubNub(pnConfiguration);

                LoginOutputData loginOutputData = new LoginOutputData(user, pubnub, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void jump() {
        loginPresenter.jumpSignup();
    }
}

package use_case.login;

import com.pubnub.api.PubNubException;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData) throws PubNubException;
    void jump();
}

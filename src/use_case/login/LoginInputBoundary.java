package use_case.login;

import com.pubnub.api.PubNubException;

/**
 * The LoginInputBoundary interface defines the input boundary for the login use case.
 * It declares methods for executing the login use case and handling a jump action.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case with the provided input data.
     *
     * @param loginInputData The input data for the login use case.
     * @throws PubNubException If an error occurs during the PubNub operation.
     */
    void execute(LoginInputData loginInputData) throws PubNubException;

    /**
     * Handles the jump action, typically transitioning to another part of the application.
     */
    void jump();
}

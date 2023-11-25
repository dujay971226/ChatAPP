package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The SignupPresenter class implements the SignupOutputBoundary interface and handles the presentation
 * logic for the signup use case. It prepares the views and communicates with the ViewManagerModel to
 * switch between different views based on the signup outcomes.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a SignupPresenter with the necessary view models and the ViewManagerModel.
     *
     * @param viewManagerModel The ViewManagerModel responsible for managing views in the application.
     * @param signupViewModel  The ViewModel for the signup interface.
     * @param loginViewModel   The ViewModel for the login interface.
     */
    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view by updating the login state and activating the login view.
     *
     * @param response The output data from a successful signup attempt.
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view by updating the signup state with an error message.
     *
     * @param error The error message to be displayed in the signup view.
     */
    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    /**
     * Jumps to the login view by activating it in the ViewManagerModel.
     */
    @Override
    public void jumpLogin() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

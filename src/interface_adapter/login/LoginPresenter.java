package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface and handles the presentation
 * logic for the login use case. It prepares the views and communicates with the ViewManagerModel to
 * switch between different views based on the login outcomes.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ProfileViewModel profileViewModel;
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a LoginPresenter with the necessary view models and the ViewManagerModel.
     *
     * @param viewManagerModel The ViewManagerModel responsible for managing views in the application.
     * @param profileViewModel The ViewModel for the user profile.
     * @param loginViewModel   The ViewModel for the login interface.
     * @param signupViewModel  The ViewModel for the signup interface.
     */
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ProfileViewModel profileViewModel,
                          LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    /**
     * Prepares the success view by updating the profile state and activating the profile view.
     *
     * @param response The output data from a successful login attempt.
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the profile view.
        ProfileState profileState = profileViewModel.getState();
        profileState.setUser(response.getUser());
        profileState.setConfig(response.getConfig());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view by updating the login state with an error message.
     *
     * @param error The error message to be displayed in the login view.
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Jumps to the signup view by activating it in the ViewManagerModel.
     */
    @Override
    public void jumpSignup() {
        this.viewManagerModel.setActiveView(this.signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

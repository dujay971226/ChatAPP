package interface_adapter.profile.profiletologout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.profiletologout.LogoutOutputBoundary;

/**
 * Presenter for handling the presentation logic of user logout operations.
 * This class implements the LogoutOutputBoundary and is responsible for
 * updating the view model based on the logout process.
 *
 * @author Xiaofeng Li
 */
public class ProfileToLogoutPresenter implements LogoutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final ProfileViewModel profileViewModel;

    /**
     * Constructs a ProfileToLogoutPresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param loginViewModel The view model for the login view.
     * @param profileViewModel The view model for the user's profile.
     */
    public ProfileToLogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    /**
     * Prepares and updates the view to reflect the user's successful logout.
     * This method updates the active view in the view manager model to the login view.
     */
    public void preparesuccessview() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}


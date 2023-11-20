package interface_adapter.profile.profiletologout;

import interface_adapter.profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.profile.profiletologout.LogoutOutputBoundary;

public class ProfileToLogoutPresenter implements LogoutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final ProfileViewModel profileViewModel;

    public ProfileToLogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }
    public void preparesuccessview() {

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}

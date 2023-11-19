package interface_adapter.Profile.profiletologout;

import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Profile.profiletologout.LogoutOutputBoundary;
import use_case.Profile.profiletologout.LogoutOutputData;

public class ProfiletologoutPresenter implements LogoutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final ProfileViewModel profileViewModel;

    public ProfiletologoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }
    public void preparesuccessview() {
        LogoutOutputData logoutOutputData = new LogoutOutputData();
        LoginState loginState = new LoginState();
        loginState.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}

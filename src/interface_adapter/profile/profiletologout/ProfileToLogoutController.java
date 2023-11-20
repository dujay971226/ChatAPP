package interface_adapter.profile.profiletologout;

import use_case.profile.profiletologout.LogoutInputBoundary;

public class ProfileToLogoutController {

    final LogoutInputBoundary logoutInputBoundary;
    public ProfileToLogoutController(LogoutInputBoundary logoutInputBoundary){
        this.logoutInputBoundary = logoutInputBoundary;
    }

    public void execute(){
        logoutInputBoundary.execute();

    }
}

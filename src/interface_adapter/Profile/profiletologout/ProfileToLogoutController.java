package interface_adapter.Profile.profiletologout;

import use_case.profiletologout.LogoutInputBoundary;

public class ProfileToLogoutController {

    final LogoutInputBoundary logoutInputBoundary;
    public ProfileToLogoutController(LogoutInputBoundary logoutInputBoundary){
        this.logoutInputBoundary = logoutInputBoundary;
    }

    public void execute(){
        logoutInputBoundary.execute();

    }
}

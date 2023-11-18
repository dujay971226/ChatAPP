package interface_adapter.Profile.profiletologout;

import interface_adapter.Profile.profiletocreate.ProfiletocreateController;
import use_case.Profile.profiletologout.LogoutInputBoundary;

public class ProfiletologoutController {

    final LogoutInputBoundary logoutInputBoundary;
    public ProfiletologoutController (LogoutInputBoundary logoutInputBoundary){
        this.logoutInputBoundary = logoutInputBoundary;
    }

    public void execute(){
        logoutInputBoundary.execute();

    }
}

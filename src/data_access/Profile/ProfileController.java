package data_access.Profile;

import entity.User;
import use_case.Profile.ProfileInputBoundary;

public class ProfileController {
    final ProfileInputBoundary profileInputBoundary;
    public ProfileController(ProfileInputBoundary profileInputBoundary){
        this.profileInputBoundary = profileInputBoundary;

    }
    public void execute(User user){this.profileInputBoundary.execute(user);}
}

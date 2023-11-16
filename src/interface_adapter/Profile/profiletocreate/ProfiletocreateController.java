package interface_adapter.Profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.Profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.Profile.profiletocreate.ProfiletocreateInputData;

public class ProfiletocreateController {
    final ProfiletocreateInputBoundary profileInputBoundary;
    public ProfiletocreateController(ProfiletocreateInputBoundary profileInputBoundary){
        this.profileInputBoundary = profileInputBoundary;

    }
    public void execute(User user, PubNub config){
        ProfiletocreateInputData profiletocreateInputData = new ProfiletocreateInputData(user,config);
        profileInputBoundary.execute(profiletocreateInputData);
    }
}

package interface_adapter.profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateInputData;

public class ProfileToCreateController {
    final ProfiletocreateInputBoundary profileInputBoundary;
    public ProfileToCreateController(ProfiletocreateInputBoundary profileInputBoundary){
        this.profileInputBoundary = profileInputBoundary;

    }
    public void execute(User user, PubNub config){
        ProfiletocreateInputData profiletocreateInputData = new ProfiletocreateInputData(user,config);
        profileInputBoundary.execute(profiletocreateInputData);
    }
}

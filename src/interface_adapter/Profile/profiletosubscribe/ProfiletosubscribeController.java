package interface_adapter.Profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.Profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.Profile.profiletocreate.ProfiletocreateInputData;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInputData;

public class ProfiletosubscribeController {
    final ProfiletosubscribeInputBoundary profileInputBoundary;
    public ProfiletosubscribeController(ProfiletosubscribeInputBoundary profileInputBoundary){
        this.profileInputBoundary = profileInputBoundary;

    }
    public void execute(User user, PubNub config){
        ProfiletosubscribeInputData profiletosubscribeInputData = new ProfiletosubscribeInputData(user,config);
        profileInputBoundary.execute(profiletosubscribeInputData);
    }
}

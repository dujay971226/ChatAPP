package interface_adapter.profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputData;

public class ProfileToSubscribeController {
    final ProfiletosubscribeInputBoundary profileInputBoundary;
    public ProfileToSubscribeController(ProfiletosubscribeInputBoundary profileInputBoundary){
        this.profileInputBoundary = profileInputBoundary;

    }
    public void execute(User user, PubNub config){
        ProfiletosubscribeInputData profiletosubscribeInputData = new ProfiletosubscribeInputData(user,config);
        profileInputBoundary.execute(profiletosubscribeInputData);
    }
}

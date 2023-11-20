package use_case.Profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;

public class ProfiletocreateOutputData {
    final private User user;
    final private PubNub config;

    public ProfiletocreateOutputData(User user, PubNub config){
        this.user = user;
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }
    public User getUser() {
        return user;
    }
}

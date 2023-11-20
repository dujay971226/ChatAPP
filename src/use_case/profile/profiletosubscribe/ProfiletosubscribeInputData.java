package use_case.profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.User;

public class ProfiletosubscribeInputData {
    final private User user;
    final private PubNub config;

    public ProfiletosubscribeInputData(User user, PubNub config){
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

package use_case.Profile.profiletocreate;
import
import com.pubnub.api.PubNub;
import entity.User;

public class ProfiletocreateInputData {
    final private User user;
    final private PubNub config;

    public ProfiletocreateInputData(User user, PubNub config){
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

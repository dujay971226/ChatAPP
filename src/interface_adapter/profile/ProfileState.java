package interface_adapter.profile;

import com.pubnub.api.PubNub;
import entity.User;

public class ProfileState {
    private User user = new User("","");
    private PubNub config;
    public ProfileState(User user, PubNub config){
        this.user = user;
        this.config = config;
    }

    public ProfileState() {}

    public void setUser(User user){
        this.user = user;
    }
    public void setConfig(PubNub config){
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }

    public User getUser() {
        return user;
    }
}

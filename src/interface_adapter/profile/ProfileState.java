package interface_adapter.profile;

import com.pubnub.api.PubNub;
import entity.User;

public class ProfileState {
    private User user = null;
    private PubNub config = null;
    public ProfileState(User user, PubNub config){
        this.user = user;
        this.config = config;
    }

    public ProfileState () {
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setConfig(PubNub config){
        this.config = config;
    }

    public PubNub getConfig() {
        return this.config;
    }

    public User getUser() {
        return this.user;
    }
}

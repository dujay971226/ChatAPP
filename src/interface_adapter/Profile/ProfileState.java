package interface_adapter.Profile;

import com.pubnub.api.PubNub;
import entity.User;

public class ProfileState {
    private User user;
    private PubNub config;
    public ProfileState(User user, PubNub config){
        this.user = user;
        this.config = config;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setConfig(PubNub config){
        this.config = config;
    }
}

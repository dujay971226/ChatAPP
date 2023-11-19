package use_case.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.List;

public class ProfiletosubscribeOutputData {
    final private User user;
    final private PubNub config;
    final private List<Channel> channels;

    public ProfiletosubscribeOutputData(User user, PubNub config, List<Channel> channels){
        this.user = user;
        this.config = config;
        this.channels = channels;
    }

    public PubNub getConfig() {
        return config;
    }
    public User getUser() {
        return user;
    }

    public List<Channel> getChannels() {
        return channels;
    }
}

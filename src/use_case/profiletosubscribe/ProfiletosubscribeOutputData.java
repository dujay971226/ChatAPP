package use_case.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProfiletosubscribeOutputData {
    final private User user;
    final private PubNub config;
    final private ArrayList<Channel> channels;

    public ProfiletosubscribeOutputData(User user, PubNub config, ArrayList<Channel> channels){
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

    public ArrayList<Channel> getChannelLog() {
        return channels;
    }
}

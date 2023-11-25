package use_case.room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

public class RoomToSettingInputData {

    private final Channel channel;
    private final User currUser;
    private final PubNub config;

    public RoomToSettingInputData(User user, Channel channel, PubNub config) {
        this.channel = channel;
        this.currUser = user;
        this.config = config;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public User getUser() {
        return this.currUser;
    }

    public PubNub getConfig() {
        return config;
    }


}

package use_case.room.room_exit;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

public class RoomExitInputData{

    private final User currUser;
    private final Channel channel;
    private final PubNub config;

    public RoomExitInputData (User user, Channel channel, PubNub config) {
        this.currUser = user;
        this.channel = channel;
        this.config = config;
    }

    public User getUser() {
        return this.currUser;
    }

    public PubNub getConfig() {
        return config;
    }

    public Channel getChannel() { return channel; }
}

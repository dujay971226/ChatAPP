package use_case.room.room_to_setting;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

public class RoomToSettingOutputData {

    private final Channel channel;
    private final User currUser;
    private final PubNub config;

    public RoomToSettingOutputData(User user, Channel channel, PubNub config) {
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

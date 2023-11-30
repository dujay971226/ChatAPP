package use_case.room.room_exit;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import entity.Channel;
import entity.User;

public class RoomExitInputData {

    private final User currUser;
    private final Channel channel;
    private final PubNub config;
    private final SubscribeCallback listener;

    public RoomExitInputData(User user, Channel channel, PubNub config, SubscribeCallback listener) {
        this.currUser = user;
        this.channel = channel;
        this.config = config;
        this.listener = listener;
    }

    public User getUser() {
        return this.currUser;
    }

    public PubNub getConfig() {
        return config;
    }

    public Channel getChannel() {
        return channel;
    }

    public SubscribeCallback getListener() {
        return listener;
    }
}

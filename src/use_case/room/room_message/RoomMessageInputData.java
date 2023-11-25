package use_case.room.room_message;
import com.pubnub.api.PubNub;
import entity.Channel;
import entity.Message;
import entity.User;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoomMessageInputData {
    private final Channel channel;

    private final User currUser;

    private final String message;

    private final PubNub config;

    public RoomMessageInputData (User user, Channel channel, PubNub config, String msg) {
        this.channel = channel;
        this.currUser = user;
        this.config = config;
        this.message = msg;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public User getUser() {
        return this.currUser;
    }

    public String getMessage() {
        return this.message;
    }

    public PubNub getConfig() {
        return config;
    }

}

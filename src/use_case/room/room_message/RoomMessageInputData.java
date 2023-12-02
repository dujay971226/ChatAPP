package use_case.room.room_message;

import com.pubnub.api.PubNub;
import entity.Channel;

public class RoomMessageInputData {
    private final Channel channel;

    private final String message;

    private final PubNub config;

    public RoomMessageInputData(Channel channel, PubNub config, String msg) {
        this.channel = channel;
        this.config = config;
        this.message = msg;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public String getMessage() {
        return this.message;
    }

    public PubNub getConfig() {
        return config;
    }

}

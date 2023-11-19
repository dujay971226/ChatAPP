package use_case.room;
import com.pubnub.api.PubNub;
import entity.Message;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoomMessageInputData {
    private Channel channel = null;

    private User currUser = null;

    private String message = "";

    private PubNub config = null;

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

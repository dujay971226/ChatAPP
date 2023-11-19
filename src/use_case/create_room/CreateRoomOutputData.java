package use_case.create_room;

import com.pubnub.api.PubNub;
import entity.Message;
import entity.User;

import java.util.ArrayList;

/**
 * Output data of create room.
 */
public class CreateRoomOutputData {

    private final String channelName;
    private final PubNub config;
    private final User user;
    private final ArrayList<Message> messageLog;

    /**
     * Initializes a CreateRoomOutputData instance.
     * @param channelName channel name
     */
    public CreateRoomOutputData(String channelName, PubNub config, User user, ArrayList<Message> messageLog) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
        this.messageLog = messageLog;
    }

    /**
     * Gets channel name.
     * @return channel name
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * Gets config.
     * @return instance of PubNub
     */
    public PubNub getConfig() {
        return this.config;
    }

    /**
     * Gets user.
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Gets message history.
     * @return message log
     */
    public ArrayList<Message> getMessageLog() {return this.messageLog; }

}

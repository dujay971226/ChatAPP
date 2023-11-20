package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Message;
import entity.User;

import java.util.ArrayList;

/**
 * Output data of subscribe room.
 * @author huangzhihao
 */
public class SubscribeRoomOutputData {
    private final String channelName;
    private final User user;
    private final PubNub config;
    private final ArrayList<Message> messageLog;

    /**
     * Initializes a SubscribeRoomOutputData instance.
     * @param channelName
     */
    public SubscribeRoomOutputData(String channelName, PubNub config, User user, ArrayList<Message> messageLog) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
        this.messageLog = messageLog;
    }

    /**
     * Gets channel name.
     * @return channel name.
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * Gets config.
     * @return config.
     */
    public PubNub getConfig() {return config; }

    /**
     * Gets user.
     * @return user
     */
    public User getUser() {return user; }

    /**
     * Gets arraylist of message history.
     * @return message log
     */
    public ArrayList<Message> getMessageLog() {return messageLog; }

}

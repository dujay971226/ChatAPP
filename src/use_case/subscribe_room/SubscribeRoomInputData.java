package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * Input data of subscribe room.
 */
public class SubscribeRoomInputData {

    private final String channelName;
    private final PubNub config;
    private final User user;
    private final ArrayList<Channel> channelLog;

    /**
     * Initializes a SubscribeRoomInputData instance.
     * @param channelName channel name
     */
    public SubscribeRoomInputData(String channelName, PubNub config, User user, ArrayList<Channel> channelLog) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
        this.channelLog = channelLog;
    }

    /**
     * Gets channel name.
     * @return channel name
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Gets config.
     * @return config
     */
    public PubNub getConfig() {return config; }

    /**
     * Gets user.
     * @return user
     */
    public User getUser() {return user; }

    /**
     * Gets channel history.
     * @return channel log
     */
    public ArrayList<Channel> getChannelLog() {return channelLog; }

}

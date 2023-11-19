package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * Input data of subscribe room.
 */
public class SubscribeRoomInputData {

    private final String channelName;
    private final PubNub config;
    private final User user;

    /**
     * Initializes a SubscribeRoomInputData instance.
     * @param channelName channel name
     */
    public SubscribeRoomInputData(String channelName, PubNub config, User user) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
    }

    /**
     * Gets the channel name.
     * @return channel name.
     */
    public String getChannelName() {
        return channelName;
    }

    public PubNub getConfig() {return config; }

    public User getUser() {return user; }

}

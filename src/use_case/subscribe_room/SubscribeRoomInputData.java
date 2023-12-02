package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * Input data of subscribe room.
 *
 * @author huangzhihao
 */
public class SubscribeRoomInputData {

    private final String channelName;
    private final PubNub config;
    private final User user;

    /**
     * Initializes a SubscribeRoomInputData instance.
     *
     * @param channelName channel name
     */
    public SubscribeRoomInputData(String channelName, PubNub config, User user) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
    }

    /**
     * Gets channel name.
     *
     * @return channel name
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Gets config.
     *
     * @return config
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Gets user.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

}

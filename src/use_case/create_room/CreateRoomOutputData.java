package use_case.create_room;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * Output data of create room.
 * @author huangzhihao
 */
public class CreateRoomOutputData {

    private final String channelName;
    private final PubNub config;
    private final User user;

    /**
     * Initializes a CreateRoomOutputData instance.
     * @param channelName channel name
     */
    public CreateRoomOutputData(String channelName, PubNub config, User user) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
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

}

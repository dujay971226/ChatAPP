package use_case.create_room;


import com.pubnub.api.PubNub;
import entity.User;

/**
 * Input data of create room.
 */
public class CreateRoomInputData {

    private final String channelName;
    private final PubNub config;
    private final User user;

    /**
     * Initializes a CreateRoomInputData instance.
     * @param channelName channel name
     * @param config instance of PubNub
     * @param user user
     */
    public CreateRoomInputData(String channelName, PubNub config, User user) {
        this.channelName = channelName;
        this.config = config;
        this.user = user;
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
}

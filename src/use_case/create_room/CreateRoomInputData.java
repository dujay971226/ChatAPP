package use_case.create_room;


import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * Input data of create room.
 */
public class CreateRoomInputData {

    private final String channelName;
    private final PubNub config;
    private final User user;
    private final ArrayList<Channel> channelLog;

    /**
     * Initializes a CreateRoomInputData instance.
     * @param channelName channel name
     * @param config instance of PubNub
     * @param user user
     * @param channelLog channel history
     */
    public CreateRoomInputData(String channelName, PubNub config, User user, ArrayList<Channel> channelLog) {
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

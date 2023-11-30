package interface_adapter.create_room;


import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * Stores the state for the CreateRoomViewModel class.
 *
 * @author huangzhihao
 */
public class CreateRoomState {

    private String channelName = "";
    private PubNub config;
    private User user;
    private String channelNameError = null;
    private ArrayList<Channel> channelLog;

    /**
     * Initializes a CreateRoomState instance.
     */
    public CreateRoomState() {
    }

    /**
     * Gets past channels.
     *
     * @return channel log
     */
    public ArrayList<Channel> getChannelLog() {
        return channelLog;
    }

    /**
     * Sets past channels.
     *
     * @param channelLog channel log
     */
    public void setChannelLog(ArrayList<Channel> channelLog) {
        this.channelLog = channelLog;
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
     * Sets channel name.
     *
     * @param channelName channel name
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Gets user.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
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
     * Sets config.
     *
     * @param config instance of PubNub class
     */
    public void setConfig(PubNub config) {
        this.config = config;
    }

    /**
     * Gets channel name error.
     *
     * @return channel name error message
     */
    public String getChannelNameError() {
        return channelNameError;
    }

    /**
     * Sets channel name error.
     *
     * @param error channel name error
     */
    public void setChannelNameError(String error) {
        this.channelNameError = error;
    }


}

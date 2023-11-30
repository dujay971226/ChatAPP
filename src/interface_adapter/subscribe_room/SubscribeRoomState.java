package interface_adapter.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * State of subscribe room.
 *
 * @author huangzhihao
 */
public class SubscribeRoomState {

    private final String noSelectionMsg = "Make a selection to join room";
    private String channelName = "";
    private ArrayList<Channel> channelLog = null;
    private String channelNameError = null;
    private PubNub config;
    private User user;

    /**
     * Initializes a SubscribeRoomState instance.
     */
    public SubscribeRoomState() {
    }

    /**
     * Gets channel name.
     *
     * @return channel name.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Sets channel name.
     *
     * @param channelName channel name.
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Gets all past channels.
     *
     * @return channel log
     */
    public ArrayList<Channel> getChannelLog() {
        return channelLog;
    }

    /**
     * Sets all past channels.
     *
     * @param channelLog channel log
     */
    public void setChannelLog(ArrayList<Channel> channelLog) {
        this.channelLog = channelLog;
    }

    /**
     * Gets no selection message.
     *
     * @return no selection message string
     */
    public String getNoSelectionMsg() {
        return noSelectionMsg;
    }

    /**
     * Gets config.
     *
     * @return instance of PubNub
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Sets config.
     *
     * @param config instance of PubNub
     */
    public void setConfig(PubNub config) {
        this.config = config;
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
     * Gets channel name error.
     *
     * @return error message
     */
    public String getChannelNameError() {
        return channelNameError;
    }

    /**
     * Sets channel name error.
     *
     * @param error error message
     */
    public void setChannelNameError(String error) {
        this.channelNameError = error;
    }
}

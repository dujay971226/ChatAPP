package interface_adapter.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * State of subscribe room.
 */
public class SubscribeRoomState {

    private String channelName = "";
    private ArrayList<Channel> channelLog = null;
    private final String noSelectionMsg = "Make a selection to join room";
    private PubNub config;
    private User user;

    /**
     * Initializes a SubscribeRoomState instance.
     */
    public SubscribeRoomState() {}

    /**
     * Gets channel name.
     * @return channel name.
     */
    public String getChannelName() {return channelName; }

    /**
     * Gets all past channels.
     * @return channel log
     */
    public ArrayList<Channel> getChannelLog() {return channelLog; }
    /**
     * Gets no selection message.
     * @return no selection message string
     */
    public String getNoSelectionMsg() {return noSelectionMsg; }

    /**
     * Gets config.
     * @return instance of PubNub
     */
    public PubNub getConfig() {return config; }

    /**
     * Gets user.
     * @return user
     */
    public User getUser() {return user; }

    /**
     * Sets channel name.
     * @param channelName channel name.
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Sets config.
     * @param config instance of PubNub
     */
    public void setConfig(PubNub config) {this.config = config; }

    /**
     * Sets user.
     * @param user user
     */
    public void setUser(User user) {this.user = user; }

    /**
     * Sets all past channels.
     * @param channelLog channel log
     */
    public void setChannelLog(ArrayList<Channel> channelLog) {this.channelLog = channelLog; }
}

package interface_adapter.create_room;


import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

/**
 * Stores the state for the CreateRoomViewModel class.
 */
public class CreateRoomState {

    private String channelName = "";
    private PubNub config;
    private User user;
    private String channelNameError = null;

    /**
     * Initializes a CreateRoomState instance.
     */
    public CreateRoomState() {}

    /**
     * Sets channel name.
     * @param channelName channel name
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Sets user.
     * @param user user
     */
    public void setUser(User user) {this.user = user; }

    /**
     * Sets config.
     * @param config instance of PubNub class
     */
    public void setConfig(PubNub config) {this.config = config; }

    /**
     * Sets channel name error.
     * @param error channel name error
     */
    public void setChannelNameError(String error) {
        this.channelNameError = error;
    }

    /**
     *  Gets channel name.
     * @return channel name
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Gets user.
     * @return user
     */
    public User getUser() {return user; }

    /**
     * Gets config.
     * @return config
     */
    public PubNub getConfig() {return config; }

    /**
     * Gets channel name error.
     * @return channel name error message
     */
    public String getChannelNameError() {return channelNameError; }



}

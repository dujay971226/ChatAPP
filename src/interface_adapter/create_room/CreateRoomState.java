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

    /**
     * Initializes a CreateRoomState instance.
     */
    public CreateRoomState() {}

    /**
     * Set channel name.
     * @param channelName channel name
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Set user.
     * @param user user
     */
    public void setUser(User user) {this.user = user; }

    /**
     * Set config.
     * @param config instance of PubNub class
     */
    public void setConfig(PubNub config) {this.config = config; }

    /**
     *  Get channel name.
     * @return channel name
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Get user.
     * @return user
     */
    public User getUser() {return user; }

    /**
     * Get config.
     * @return config
     */
    public PubNub getConfig() {return config; }




}

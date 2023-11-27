package use_case.profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * The ProfiletosubscribeOutputData class represents the output data containing user information,
 * configuration, and a list of subscribed channels.
 *
 * @author Xiaofeng Li
 */
public class ProfiletosubscribeOutputData {
    private final User user;
    private final PubNub config;
    private final ArrayList<Channel> channels;

    /**
     * Constructs a new ProfiletosubscribeOutputData instance.
     *
     * @param user     The user associated with the output data.
     * @param config   The PubNub configuration.
     * @param channels The list of subscribed channels.
     */
    public ProfiletosubscribeOutputData(User user, PubNub config, ArrayList<Channel> channels) {
        this.user = user;
        this.config = config;
        this.channels = channels;
    }

    /**
     * Gets the PubNub configuration.
     *
     * @return The PubNub configuration.
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Gets the user associated with the output data.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the list of subscribed channels.
     *
     * @return The list of subscribed channels.
     */
    public ArrayList<Channel> getChannelLog() {
        return channels;
    }
}


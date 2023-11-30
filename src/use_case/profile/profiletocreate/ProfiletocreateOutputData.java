package use_case.profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * This class represents the output data after creating a profile.
 */
public class ProfiletocreateOutputData {
    final private User user;
    final private PubNub config;
    final private ArrayList<Channel> channels;

    /**
     * Constructs a ProfiletocreateOutputData instance with the specified user and configuration.
     *
     * @param user   The user information for the created profile.
     * @param config The PubNub configuration for the user.
     */
    public ProfiletocreateOutputData(User user, PubNub config, ArrayList<Channel> channels) {
        this.user = user;
        this.config = config;
        this.channels = channels;
    }

    /**
     * Gets the channels as an arraylist.
     * @return an arraylist of channels.
     */
    public ArrayList<Channel> getChannelLog() {return channels; }
    /**
     * Get the PubNub configuration of the created profile.
     *
     * @return The PubNub configuration.
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Get the user information of the created profile.
     *
     * @return The user information.
     */
    public User getUser() {
        return user;
    }
}


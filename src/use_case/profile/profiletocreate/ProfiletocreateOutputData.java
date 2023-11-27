package use_case.profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * This class represents the output data after creating a profile.
 */
public class ProfiletocreateOutputData {
    final private User user;
    final private PubNub config;

    /**
     * Constructs a ProfiletocreateOutputData instance with the specified user and configuration.
     *
     * @param user   The user information for the created profile.
     * @param config The PubNub configuration for the user.
     */
    public ProfiletocreateOutputData(User user, PubNub config) {
        this.user = user;
        this.config = config;
    }

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


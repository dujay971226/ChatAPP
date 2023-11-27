package use_case.profile.profiletocreate;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * The ProfiletocreateInputData class represents the input data required for creating a user profile.
 * It contains information about the user and configuration settings.
 *
 * @author Xiaofeng Li
 */
public class ProfiletocreateInputData {
    private final User user;      // The user information for profile creation.
    private final PubNub config;  // The configuration settings for the user.

    /**
     * Constructs a new ProfiletocreateInputData object with the specified user and configuration.
     *
     * @param user   The user information for profile creation.
     * @param config The configuration settings for the user.
     */
    public ProfiletocreateInputData(User user, PubNub config) {
        this.user = user;
        this.config = config;
    }

    /**
     * Get the configuration settings for the user.
     *
     * @return The configuration settings.
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Get the user information for profile creation.
     *
     * @return The user information.
     */
    public User getUser() {
        return user;
    }
}


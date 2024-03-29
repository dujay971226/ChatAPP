package interface_adapter.profile;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * Represents the state of a user's profile in the application.
 * This class holds data related to the current state of profile-related operations,
 * such as the user's information and configuration settings.
 *
 * @author Xiaofeng Li
 */
public class ProfileState {
    private User user = new User("", "");
    private PubNub config;

    /**
     * Constructs a new ProfileState instance with specified user and configuration.
     *
     * @param user   The user associated with this profile.
     * @param config The configuration settings for the profile.
     */
    public ProfileState(User user, PubNub config) {
        this.user = user;
        this.config = config;
    }

    /**
     * Default constructor for ProfileState. Initializes with default user and no configuration.
     */
    public ProfileState() {
    }

    /**
     * Retrieves the configuration of this profile.
     *
     * @return The current PubNub configuration.
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Sets the configuration for this profile state.
     *
     * @param config The new configuration to be set.
     */
    public void setConfig(PubNub config) {
        this.config = config;
    }

    /**
     * Retrieves the user of this profile.
     *
     * @return The current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for this profile state.
     *
     * @param user The new user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}

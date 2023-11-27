package use_case.profile.profiletosubscribe;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * The ProfiletosubscribeInputData class represents the input data for a subscription operation.
 * It contains user and configuration information required for the subscription.
 *
 * @author Xiaofeng Li
 */
public class ProfiletosubscribeInputData {
    private final User user;
    private final PubNub config;

    /**
     * Constructs a new ProfiletosubscribeInputData object with user and configuration information.
     *
     * @param user   The user for whom the subscription is performed.
     * @param config The PubNub configuration for the subscription.
     */
    public ProfiletosubscribeInputData(User user, PubNub config) {
        this.user = user;
        this.config = config;
    }

    /**
     * Get the PubNub configuration for the subscription.
     *
     * @return The PubNub configuration.
     */
    public PubNub getConfig() {
        return config;
    }

    /**
     * Get the user for whom the subscription is performed.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }
}


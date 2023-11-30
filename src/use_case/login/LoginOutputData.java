package use_case.login;

import com.pubnub.api.PubNub;
import entity.User;

/**
 * Represents the output data produced by a login use case.
 */
public class LoginOutputData {
    private final User user;
    private final boolean useCaseFailed;
    private final PubNub config;

    /**
     * Constructs a new instance of LoginOutputData.
     *
     * @param user          The authenticated user.
     * @param config        Configuration data.
     * @param useCaseFailed Indicates whether the use case encountered an error.
     */
    public LoginOutputData(User user, PubNub config, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
        this.config = config;
    }

    /**
     * Gets the authenticated user.
     *
     * @return The authenticated user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the configuration data.
     *
     * @return Configuration data.
     */
    public PubNub getConfig() {
        return config;
    }
}



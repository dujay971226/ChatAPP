package use_case.login;

import com.pubnub.api.PubNub;
import entity.User;

public class LoginOutputData {
    private final User user;
    private boolean useCaseFailed;
    private PubNub config;

    public LoginOutputData(User user, PubNub config, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
        this.config = config;
    }

    public User getUser() {
        return user;
    }

    public PubNub getConfig() {
        return config;
    }
}



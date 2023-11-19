package use_case.room;

import com.pubnub.api.PubNub;
import entity.User;

public class RoomExitInputData{

    private User currUser = null;
    private PubNub config = null;

    public RoomExitInputData (User user, PubNub config) {
        this.currUser = user;
        this.config = config;
    }

    public User getUser() {
        return this.currUser;
    }

    public PubNub getConfig() {
        return config;
    }

}

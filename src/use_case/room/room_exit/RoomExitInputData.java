package use_case.room.room_exit;

import com.pubnub.api.PubNub;
import entity.User;

public class RoomExitInputData{

    private final User currUser;
    private final PubNub config;

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

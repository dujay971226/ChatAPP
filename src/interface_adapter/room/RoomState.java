package interface_adapter.room;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;

public class RoomState {


    public void setConfig(PubNub config) {};
    public void setChannel(Channel channel) {};

    public void setUser(User user) {};
}

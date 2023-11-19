package interface_adapter.room;

import java.util.ArrayList;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import entity.Channel;
import entity.Message;
import entity.User;

public class RoomState {


    public void setConfig(PubNub config) {};
    public void setChannel(Channel channel) {};

    public void setUser(User user) {};
    public void setMessageLog(ArrayList<Message> messageLog) {};
}

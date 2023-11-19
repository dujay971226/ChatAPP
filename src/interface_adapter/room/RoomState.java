package interface_adapter.room;

import com.pubnub.api.PubNub;
import entity.Message;

import java.util.ArrayList;

public class RoomState {

    private Channel channel = null;

    private User currUser = null;

    private ArrayList<Message> messageLog = new ArrayList<Message>();

    private String message = null;

    private PubNub config = null;

    public RoomState(RoomState copy) {
        this.channel = copy.getChannel();
        this.currUser = copy.getUser();
        this.messageLog = copy.getMessageLog();
        this.message = copy.getMessage();
    }

    public RoomState () {

    }

    public Channel getChannel() {
        return this.channel;
    }

    public User getUser() {
        return this.currUser;
    }

    public ArrayList<Message> getMessageLog() {
        return this.messageLog;
    }

    public String getMessage() {
        return this.message;
    }

    public PubNub getConfig() {
        return config;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setUser(User user) {
        this.currUser = user;
    }

    public void setMessageLog(ArrayList<Message> msgs) {
        this.messageLog = msgs;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setConfig(PubNub config) {
        this.config = config;
    }
}

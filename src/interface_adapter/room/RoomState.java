package interface_adapter.room;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.Message;
import entity.User;

import java.util.ArrayList;

//The current state of RoomView
public class RoomState {

    //THe current Channel info
    private Channel channel = null;

    //The current logged-in user info
    private User currUser = null;

    //Message history to load
    private ArrayList<Message> messageLog = new ArrayList<Message>();

    private String message = null;

    private PubNub config = null;

    //To warn a message history was loaded
    private boolean LOG_UPDATE = false;

    //To warn a new message was received
    private boolean NEW_MESSAGE_UPDATE = false;

    public RoomState(RoomState copy) {
        this.channel = copy.getChannel();
        this.currUser = copy.getUser();
        this.messageLog = copy.getMessageLog();
        this.message = copy.getMessage();
        this.config = copy.getConfig();
        this.LOG_UPDATE = copy.getLOG_UPDATE();
        this.NEW_MESSAGE_UPDATE = copy.getNEW_MESSAGE_UPDATE();
    }

    public RoomState() throws PubNubException {
        UserId userId = new UserId("Jay");
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        PubNub pubnub = new PubNub(pnConfiguration);
        this.config = pubnub;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getUser() {
        return this.currUser;
    }

    public void setUser(User user) {
        this.currUser = user;
    }

    public ArrayList<Message> getMessageLog() {
        return this.messageLog;
    }

    public void setMessageLog(ArrayList<Message> msgs) {
        this.messageLog = msgs;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public PubNub getConfig() {
        return config;
    }

    public void setConfig(PubNub config) {
        this.config = config;
    }

    public boolean getLOG_UPDATE() {
        return this.LOG_UPDATE;
    }

    public boolean getNEW_MESSAGE_UPDATE() {
        return this.NEW_MESSAGE_UPDATE;
    }

    public void setNotice() {
        this.LOG_UPDATE = true;
    }

    public void setOffNotice() {
        this.LOG_UPDATE = false;
    }

    public void setReceiveMessageNotice() {
        this.NEW_MESSAGE_UPDATE = true;
    }

    public void setOffReceiveMessageNotice() {
        this.NEW_MESSAGE_UPDATE = false;
    }
}

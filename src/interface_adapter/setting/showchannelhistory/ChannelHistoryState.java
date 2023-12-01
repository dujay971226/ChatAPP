package interface_adapter.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import entity.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelHistoryState {
    private ArrayList<Message> channelMessages;
    private String channelMessageError;
    private String deleteMessageError;
    private boolean Active = false;
    private boolean UpdateDelete = false;

    private PubNub config;
    private String channel;
    private HashMap<Long, String> deleteMessages = new HashMap<>();

    public ChannelHistoryState() {
    }

    public ChannelHistoryState(ChannelHistoryState copy) {
        this.channelMessages = copy.channelMessages;
        this.channelMessageError = copy.channelMessageError;
        this.config = copy.config;
        this.channel = copy.channel;
        this.deleteMessages = copy.deleteMessages;
    }

    public String getDeleteMessageError() {
        return deleteMessageError;
    }

    public void setDeleteMessageError(String deleteMessageError) {
        this.deleteMessageError = deleteMessageError;
    }

    public ArrayList<Message>  getChannelMessages() {
        return channelMessages;
    }

    public void setChannelMessages(ArrayList<Message>  channelMessages) {
        this.channelMessages = channelMessages;
    }

    public String getChannelMessageError() {
        return channelMessageError;
    }

    public void setChannelMessageError(String error) {
        this.channelMessageError = error;
    }

    public PubNub getConfig() {
        return config;
    }

    public void setConfig(PubNub config) {
        this.config = config;
    }

    public boolean isUpdateDelete() {
        return UpdateDelete;
    }

    public void setUpdateDelete(boolean updateDelete) {
        UpdateDelete = updateDelete;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public HashMap<Long, String> getDeleteMessages() {
        return deleteMessages;
    }

    public void setDeleteMessages(HashMap<Long, String> deleteMessages) {
        this.deleteMessages = deleteMessages;
    }
}

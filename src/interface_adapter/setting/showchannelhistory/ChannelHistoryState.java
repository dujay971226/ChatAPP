package interface_adapter.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import entity.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ChannelHistoryState {
    private List<PNFetchMessageItem> channelMessages;
    private String channelMessageError;
    private String deleteMessageError;

    private PubNub config;
    private String channel;
    private HashMap<Long, String> deleteMessages = new HashMap<>();

    public ChannelHistoryState() {
    }

    public ChannelHistoryState(ChannelHistoryState copy){
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

    public List<PNFetchMessageItem> getChannelMessages() {
        return channelMessages;
    }

    public void setChannelMessages(List<PNFetchMessageItem> channelMessages) {
        this.channelMessages = channelMessages;
    }

    public void setChannelMessageError(String error){
        this.channelMessageError = error;
    }

    public String getChannelMessageError() {
        return channelMessageError;
    }

    public PubNub getConfig() {
        return config;
    }

    public void setConfig(PubNub config) {
        this.config = config;
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

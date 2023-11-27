package interface_adapter.setting.showchannelhistory;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;

import java.util.List;

public class ChannelHistoryState {
    private List<PNFetchMessageItem> channelMessages;
    private String channelMessageError;

    public ChannelHistoryState() {
    }

    public ChannelHistoryState(ChannelHistoryState copy){
        this.channelMessages = copy.channelMessages;
        this.channelMessageError = copy.channelMessageError;
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
}

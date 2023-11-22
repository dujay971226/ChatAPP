package interface_adapter.showchannelhistory;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;

import java.util.List;

public class ChannelHistoryState {
    private List<PNFetchMessageItem> channelMessages;

    public ChannelHistoryState() {
    }

    public ChannelHistoryState(ChannelHistoryState copy){
        this.channelMessages = copy.channelMessages;
    }

    public List<PNFetchMessageItem> getChannelMessages() {
        return channelMessages;
    }

    public void setChannelMessages(List<PNFetchMessageItem> channelMessages) {
        this.channelMessages = channelMessages;
    }
}

package use_case.showchannelhistory;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;

import java.util.List;

public class ShowChannelHistoryOutputData {
    private final List<PNFetchMessageItem> channelMessages;

    public ShowChannelHistoryOutputData(List<PNFetchMessageItem> channelMessages) {
        this.channelMessages = channelMessages;
    }

    public List<PNFetchMessageItem> getChannelMessages() {
        return channelMessages;
    }
}

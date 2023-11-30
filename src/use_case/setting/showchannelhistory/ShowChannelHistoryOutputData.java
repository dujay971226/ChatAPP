package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;

import java.util.List;

public class ShowChannelHistoryOutputData {
    private final List<PNFetchMessageItem> channelMessages;
    private PubNub config;
    private String channelName;

    public PubNub getConfig() {
        return config;
    }

    public String getChannelName() {
        return channelName;
    }

    public ShowChannelHistoryOutputData(List<PNFetchMessageItem> channelMessages, PubNub config, String channelName) {
        this.channelMessages = channelMessages;
        this.config = config;
        this.channelName = channelName;
    }

    public List<PNFetchMessageItem> getChannelMessages() {
        return channelMessages;
    }
}

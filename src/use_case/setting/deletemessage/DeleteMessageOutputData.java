package use_case.setting.deletemessage;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;

import java.util.List;

public class DeleteMessageOutputData {
    private final List<PNFetchMessageItem> channelMessages;

    public DeleteMessageOutputData(List<PNFetchMessageItem> channelMessages) {
        this.channelMessages = channelMessages;
    }

    public List<PNFetchMessageItem> getChannelMessages() {
        return channelMessages;
    }
}

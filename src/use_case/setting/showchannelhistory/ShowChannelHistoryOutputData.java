package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import entity.Message;

import java.util.ArrayList;

public class ShowChannelHistoryOutputData {
    private final ArrayList<Message> channelMessages;
    private final PubNub config;
    private final String channelName;

    public ShowChannelHistoryOutputData(ArrayList<Message> channelMessages, PubNub config, String channelName) {
        this.channelMessages = channelMessages;
        this.config = config;
        this.channelName = channelName;
    }

    public PubNub getConfig() {
        return config;
    }

    public String getChannelName() {
        return channelName;
    }

    public ArrayList<Message> getChannelMessages() {
        return channelMessages;
    }
}

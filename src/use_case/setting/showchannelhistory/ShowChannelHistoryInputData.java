package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;

import java.time.LocalDateTime;

public class ShowChannelHistoryInputData {
    private final LocalDateTime endDateTime;
    private final String channelName;
    private final PubNub config;

    public ShowChannelHistoryInputData(LocalDateTime localDateTime, String channelName, PubNub config) {
        this.endDateTime = localDateTime;
        this.channelName = channelName;
        this.config = config;
    }

    public LocalDateTime getEndTime() {
        return this.endDateTime;
    }

    public PubNub getConfig() {
        return config;
    }

    public String getChannelName() {
        return this.channelName;
    }

}

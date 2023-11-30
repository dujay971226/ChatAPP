package use_case.setting.settingtochannelhistory;

import com.pubnub.api.PubNub;

public class SettingToChannelHistoryOutputData {
    private String channelName;
    private PubNub config;

    public SettingToChannelHistoryOutputData(String channelName, PubNub config) {
        this.config = config;
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public PubNub getConfig() {
        return config;
    }
}

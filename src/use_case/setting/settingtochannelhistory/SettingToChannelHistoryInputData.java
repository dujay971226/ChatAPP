package use_case.setting.settingtochannelhistory;

import com.pubnub.api.PubNub;

public class SettingToChannelHistoryInputData {
    private String channelName;
    private PubNub config;

    public String getChannelName() {
        return channelName;
    }

    public PubNub getConfig() {
        return config;
    }

    public SettingToChannelHistoryInputData(String channelName, PubNub config){
        this.config = config;
        this.channelName = channelName;
    }

}

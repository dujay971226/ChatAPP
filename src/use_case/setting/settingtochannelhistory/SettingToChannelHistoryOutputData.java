package use_case.setting.settingtochannelhistory;

import com.pubnub.api.PubNub;
import entity.User;

public class SettingToChannelHistoryOutputData {
    private final String channelName;
    private final PubNub config;
    private final User user;

    public SettingToChannelHistoryOutputData(String channelName, PubNub config, User user) {
        this.config = config;
        this.channelName = channelName;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getChannelName() {
        return channelName;
    }

    public PubNub getConfig() {
        return config;
    }
}

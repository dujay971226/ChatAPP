package use_case.setting.channelsetting;

import com.pubnub.api.PubNub;

public class ShowSettingInputData {
    private final PubNub config;
    private String currentChannel;

    public ShowSettingInputData(String currentChannel, PubNub config) {
        this.currentChannel = currentChannel;
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }

    public String getCurrentChannel() {
        return this.currentChannel;
    }
}

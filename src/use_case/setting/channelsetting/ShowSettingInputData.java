package use_case.setting.channelsetting;

import com.pubnub.api.PubNub;

public class ShowSettingInputData {
    private String currentChannel;
    private final PubNub config;

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

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;

    }
}

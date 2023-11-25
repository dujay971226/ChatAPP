package use_case.setting.channelsetting;

import com.pubnub.api.PubNub;

public class ShowSettingInputData {
    private String currentChannel;
    private PubNub config;

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;

    }

    public PubNub getConfig() {
        return config;
    }

    public ShowSettingInputData(String currentChannel, PubNub config) {
        this.currentChannel = currentChannel;
        this.config = config;
    }

    public String getCurrentChannel(){
        return this.currentChannel;
    }
}

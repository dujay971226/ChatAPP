package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;

public class DeleteMessageInputData {
    private final String channelName;
    private final PubNub config;
    // Since we will process the data inside the interactor, then it would be redundant to cast the Object[] array to Long[] in the view
    private Object[] startTimeLists;

    public DeleteMessageInputData(Object[] startTime, String channelName, PubNub config) {
        this.startTimeLists = startTime;
        this.channelName = channelName;
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }

    public Object[] getStartTimeLists() {
        return startTimeLists;
    }

    public String getChannelName() {
        return this.channelName;
    }

}

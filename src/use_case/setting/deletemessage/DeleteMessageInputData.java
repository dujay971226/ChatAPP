package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;

public class DeleteMessageInputData {
    private final String channelName;
    private final PubNub config;
    // Since we will process the data inside the interactor, then it would be redundant to cast the Object[] array to Long[] in the view
    private Object[] startTimes;
    private Object[] endTimes;

    public DeleteMessageInputData(Object[] startTimes, Object[] endTimes, String channelName, PubNub config) {
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.channelName = channelName;
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }

    public Object[] getStartTimeLists() {
        return startTimes;
    }
    public Object[] getEndTimesLists() {
        return endTimes;
    }

    public String getChannelName() {
        return this.channelName;
    }

}

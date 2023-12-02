package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;
import entity.Message;

import java.util.HashMap;

public class DeleteMessageInputData {
    private final String channelName;
    private final PubNub config;
    // Since we will process the data inside the interactor, then it would be redundant to cast the Object[] array to Long[] in the view
    private Object[] startTimes;
    private HashMap<Long, Message> deleteMessages;



    public DeleteMessageInputData(Object[] startTimes, HashMap<Long, Message> deleteMessages, String channelName, PubNub config) {
        this.startTimes = startTimes;
        this.deleteMessages = deleteMessages;
        this.channelName = channelName;
        this.config = config;
    }

    public HashMap<Long, Message> getDeleteMessages() {
        return deleteMessages;
    }
    public PubNub getConfig() {
        return config;
    }

    public Object[] getStartTimeLists() {
        return startTimes;
    }

    public String getChannelName() {
        return this.channelName;
    }

}

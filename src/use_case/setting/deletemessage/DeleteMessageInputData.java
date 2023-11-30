package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;

public class DeleteMessageInputData{
    private long startTime;

    // Since we will process the data inside the interactor, then it would be redundant to cast the Object[] array to Long[] in the view
    private Object[] startTimeLists;
    private long endTime;
    private String channelName;
    private PubNub config;



    public DeleteMessageInputData(long startTime, long endTime, String channelName, PubNub config){
        this.startTime = startTime;
        this.endTime = endTime;
        this.channelName = channelName;
        this.config = config;
    }

    public DeleteMessageInputData(Object[] startTime, String channelName, PubNub config){
        this.startTimeLists = startTime;
        this.channelName = channelName;
        this.config = config;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public PubNub getConfig() {
        return config;
    }

    public Object[] getStartTimeLists() {
        return startTimeLists;
    }

    public String getChannelName(){
        return this.channelName;
    }

}

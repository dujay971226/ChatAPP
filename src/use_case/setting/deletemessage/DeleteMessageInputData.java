package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;

import java.time.LocalDateTime;

public class DeleteMessageInputData{
    private long startTime;
    private long[] startTimeLists;
    private long endTime;
    private String channelName;
    private PubNub config;

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public DeleteMessageInputData(long startTime, long endTime, String channelName, PubNub config){
        this.startTime = startTime;
        this.endTime = endTime;
        this.channelName = channelName;
        this.config = config;
    }

    public DeleteMessageInputData(long[] startTime, String channelName, PubNub config){
        this.startTimeLists = startTime;
        this.channelName = channelName;
        this.config = config;
    }

    public PubNub getConfig() {
        return config;
    }

    public String getChannelName(){
        return this.channelName;
    }

}

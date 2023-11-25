package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;

import java.time.LocalDateTime;

public class ShowChannelHistoryInputData{
    private LocalDateTime endDateTime;
    private String channelName;
    private PubNub config;
    public ShowChannelHistoryInputData(LocalDateTime localDateTime, String channelName, PubNub config){
        this.endDateTime = localDateTime;
        this.channelName = channelName;
        this.config = config;
    }
    public LocalDateTime getEndTime(){
        return this.endDateTime;
    }

    public PubNub getConfig() {
        return config;
    }

    public String getChannelName(){
        return this.channelName;
    }

}

package use_case.showchannelhistory;

import java.time.LocalDateTime;

public class ShowChannelHistoryInputData{
    private LocalDateTime endDateTime;
    private String channelName;

    public ShowChannelHistoryInputData(LocalDateTime localDateTime, String channelName){
        this.endDateTime = localDateTime;
        this.channelName = channelName;
    }
    public LocalDateTime getEndTime(){
        return this.endDateTime;
    }

    public String getChannelName(){
        return this.channelName;
    }

}

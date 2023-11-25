package use_case.setting.channelsetting;

import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;

import java.util.Collection;

public class ShowSettingOutputData {
    private Collection<PNHereNowChannelData> channelOccupancy;

    public ShowSettingOutputData(Collection<PNHereNowChannelData> channelOccupancy) {
        this.channelOccupancy = channelOccupancy;
    }

    public Collection<PNHereNowChannelData> getChannelOccupancy() {
        return channelOccupancy;
    }

    public void setChannelOccupancy(Collection<PNHereNowChannelData> channelOccupancy) {
        this.channelOccupancy = channelOccupancy;
    }
}

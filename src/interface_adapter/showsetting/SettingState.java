package interface_adapter.showsetting;

import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;

import java.util.Collection;

public class SettingState {

    private String channelMessageError;

    private String loadingSubscribersError;

    private Collection<PNHereNowChannelData> channelOccupancy;

    public Collection<PNHereNowChannelData> getChannelOccupancy() {
        return channelOccupancy;
    }

    public void setChannelOccupancy(Collection<PNHereNowChannelData> channelOccupancy) {
        this.channelOccupancy = channelOccupancy;
    }

    public SettingState(SettingState copy) {
        this.channelMessageError = copy.channelMessageError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SettingState() {}

    public String getLoadingSubscribersError() {
        return loadingSubscribersError;
    }

    public void setLoadingSubscribersError(String loadingSubscribersError) {
        this.loadingSubscribersError = loadingSubscribersError;
    }

    public void setChannelMessageError(String error){
        this.channelMessageError = error;
    }

    public String getChannelMessageError() {
        return channelMessageError;
    }
}

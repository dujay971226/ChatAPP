package interface_adapter.setting.showsetting;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import entity.Channel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomViewModel;

import java.util.Collection;

public class SettingState {

    private String loadingSubscribersError;
    private PubNub config;
    private boolean isActive = false;
    private User user;
    private Channel channel;

    public void setConfig(PubNub config) {
        this.config = config;
        this.isActive = true;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveState(boolean active) {
        isActive = active;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public PubNub getConfig() {
        return config;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    private Collection<PNHereNowChannelData> channelOccupancy;

    public Collection<PNHereNowChannelData> getChannelOccupancy() {
        return channelOccupancy;
    }

    public void setChannelOccupancy(Collection<PNHereNowChannelData> channelOccupancy) {
        this.channelOccupancy = channelOccupancy;
    }

    public SettingState(SettingState copy) {
        this.loadingSubscribersError = copy.getLoadingSubscribersError();
        this.config = copy.getConfig();
        this.isActive = copy.isActive();
        this.user = copy.getUser();
        this.channel = copy.getChannel();
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SettingState() {}

    public String getLoadingSubscribersError() {
        return loadingSubscribersError;
    }

    public void setLoadingSubscribersError(String loadingSubscribersError) {
        this.loadingSubscribersError = loadingSubscribersError;
    }


}

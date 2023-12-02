package interface_adapter.setting.showsetting;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import entity.Channel;
import entity.Message;
import entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class SettingState {

    private String loadingSubscribersError;
    private PubNub config;
    private boolean isActive = false;
    private User user;
    private Channel channel;
    private Collection<PNHereNowChannelData> channelOccupancy;
    private ArrayList<Message> channelHistory;

    public SettingState(SettingState copy) {
        this.loadingSubscribersError = copy.getLoadingSubscribersError();
        this.config = copy.getConfig();
        this.isActive = copy.isActive();
        this.user = copy.getUser();
        this.channel = copy.getChannel();
        this.channelOccupancy = copy.channelOccupancy;
        this.channelHistory = copy.channelHistory;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SettingState() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveState(boolean active) {
        isActive = active;
    }

    public PubNub getConfig() {
        return config;
    }

    public void setConfig(PubNub config) {
        this.config = config;
        this.isActive = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ArrayList<Message> getChannelHistory() {
        return channelHistory;
    }

    public void setChannelHistory(ArrayList<Message> channelHistory) {
        this.channelHistory = channelHistory;
    }

    public Collection<PNHereNowChannelData> getChannelOccupancy() {
        return channelOccupancy;
    }

    public void setChannelOccupancy(Collection<PNHereNowChannelData> channelOccupancy) {
        this.channelOccupancy = channelOccupancy;
    }

    public String getLoadingSubscribersError() {
        return loadingSubscribersError;
    }

    public void setLoadingSubscribersError(String loadingSubscribersError) {
        this.loadingSubscribersError = loadingSubscribersError;
    }


}

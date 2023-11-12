package interface_adapter.subscribe_room;

public class SubscribeRoomState {
    private String channelName = "";
    private String channelNameError = null;

    public SubscribeRoomState() {}

    public String getChannelName() {return channelName; }

    public String getChannelNameError() {return channelNameError; }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setChannelNameError(String error) {
        this.channelNameError = error;
    }

}

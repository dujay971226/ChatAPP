package interface_adapter.create_room;


public class CreateRoomState {
    private String channelName = "";
    private String channelNameError = null;

    public CreateRoomState() {}

    public String getChannelName() {
        return channelName;
    }

    public String getChannelNameError() {
        return channelNameError;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setChannelNameError(String error) {
        this.channelNameError = error;
    }
}

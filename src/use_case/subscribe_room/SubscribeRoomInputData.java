package use_case.subscribe_room;

public class SubscribeRoomInputData {

    private final String channelName;

    public SubscribeRoomInputData(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

}

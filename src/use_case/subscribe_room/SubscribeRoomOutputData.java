package use_case.subscribe_room;

public class SubscribeRoomOutputData {
    private final String channelName;

    public SubscribeRoomOutputData(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return this.channelName;
    }
}

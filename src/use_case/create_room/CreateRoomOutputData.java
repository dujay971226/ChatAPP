package use_case.create_room;

public class CreateRoomOutputData {

    private final String channelName;

    public CreateRoomOutputData(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return this.channelName;
    }
}

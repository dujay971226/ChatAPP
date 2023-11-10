package use_case.create_room;



/**
 *
 */
public class CreateRoomInputData {

    final private String channelName;

    public CreateRoomInputData(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return this.channelName;
    }

}

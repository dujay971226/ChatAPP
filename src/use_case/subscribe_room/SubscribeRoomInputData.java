package use_case.subscribe_room;

/**
 * Input data of subscribe room.
 */
public class SubscribeRoomInputData {

    private final String channelName;

    /**
     * Initializes a SubscribeRoomInputData instance.
     * @param channelName channel name
     */
    public SubscribeRoomInputData(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Gets the channel name.
     * @return channel name.
     */
    public String getChannelName() {
        return channelName;
    }

}

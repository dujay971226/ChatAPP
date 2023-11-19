package use_case.subscribe_room;

/**
 * Output data of subscribe room.
 */
public class SubscribeRoomOutputData {
    private final String channelName;

    /**
     * Initializes a SubscribeRoomOutputData instance.
     * @param channelName
     */
    public SubscribeRoomOutputData(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Gets channel name.
     * @return channel name.
     */
    public String getChannelName() {
        return this.channelName;
    }
}

package use_case.subscribe_room;

import entity.Channel;
import entity.User;

/**
 * Data access interface of subscribe room.
 */
public interface SubscribeRoomDataAccessInterface {
    /**
     * Saves channel name and username to locally.
     * @param channelName channel name
     * @param userName username
     */
    void save(String channelName, String userName);

    /**
     * Gets all possible channel names.
     * @return array of channel names
     */
    String[] getChannelNames();
}

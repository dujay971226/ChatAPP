package use_case.create_room;

import entity.Channel;
import entity.User;

/**
 * Data access interface of create room.
 */
public interface CreateRoomDataAccessInterface {

    /**
     * Determines whether a channel name exists.
     * @param identifier a possible channel name
     * @return true if name exists, false otherwise
     */
    boolean existsByName(String identifier);

    /**
     * Saves the channel name locally.
     * @param channelName channel name
     */
    void save(String channelName, User user);
}

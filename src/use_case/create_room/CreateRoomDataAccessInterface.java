package use_case.create_room;

import entity.Channel;
import entity.User;

import java.time.LocalDateTime;

// Not used.
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
     * Saves the channel name and username locally.
     * @param channelName channel name
     * @param user user
     */
    void save(String channelName, User user);
}

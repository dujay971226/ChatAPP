package data_access;

import entity.Channel;
import entity.User;

import java.util.ArrayList;

/**
 * Interface for a data access object that manages channel and user data.
 * This interface declares methods for retrieving channels associated with a user
 * and saving user data to a channel.
 * @author Xiaofeng Li
 */
public interface iChannelDataAccessObject {

    /**
     * Retrieves a list of channels that a given user is associated with.
     * @param user The user for whom to retrieve the channel list.
     * @return An ArrayList of Channels associated with the given user.
     */
    ArrayList<Channel> getChannels(User user);

    /**
     * Saves the specified user data to the specified channel in a data source.
     * @param channelName The channel name of the channel to be be saved.
     * @param curr The current user whose data is to be saved.
     */
    void save(String channelName, User curr);
}

package entity;

/**
 * Represents a channel in the application.
 * This class provides the basic structure for a channel, including its name and the current user.
 *
 * @author Xiaofeng Li
 */
public class Channel {
    private final String channelName;
    private User currUser;

    /**
     * Constructs a new Channel instance.
     *
     * @param channelName The name of the channel.
     * @param currUser    The current user associated with this channel, can be null.
     */
    public Channel(String channelName, User currUser) {
        this.channelName = channelName;
        this.currUser = currUser;
    }

    /**
     * Retrieves the name of the channel.
     *
     * @return The name of the channel.
     */
    public String getName() {
        return this.channelName;
    }

    /**
     * Retrieves the current user associated with the channel.
     *
     * @return The current user of the channel.
     */
    public User getUser() {
        return this.currUser;
    }

    /**
     * Sets the current user of the channel.
     *
     * @param currUser The user to be set as the current user of the channel.
     */
    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
}

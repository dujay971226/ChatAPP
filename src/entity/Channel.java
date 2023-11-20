package entity;

public class Channel {
    private final String channelName;

    private final User currUser;

    public Channel(String channelName, User currUser){
        this.channelName = channelName;
        this.currUser = currUser;

    }

    public String getName(){
        return this.channelName;
    }

    public User getUser(){
        return this.currUser;
    }


}
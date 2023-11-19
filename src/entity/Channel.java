package entity;

public class Channel {
    private final String channelname;
    private final User currUser;

    public Channel(String name, User currUser){
        this.channelname = name;
        this.currUser = currUser;

    }

    public String getName(){
        return this.channelname;
    }

    public User getUser(){
        return this.currUser;
    }


}


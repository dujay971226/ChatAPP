package entity;

public class Channel {
    private final String channelName;

    private final User currUser;

    public Channel(String name, User currUser){
        this.channelName = name;
        this.currUser = currUser;
    }

    public String getName(){
        return this.channelName;
    }

    public User getCurrUser() {return this.currUser; }


}
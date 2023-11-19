package entity;

public class Channel {
    private final String channelname;

    private final String token;

    public Channel(String name, String token){
        this.channelname = name;
        this.token = token;
    }

    public String getName(){
        return this.channelname;
    }

    public String getToken(){
        return this.token;
    }


}
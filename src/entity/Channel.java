package entity;

public class Channel {
    private final String channelname;

    private final String token;

    public Channel(String name, String token){
        this.channelname = name;
        this.token = token;

    }

    public String getname(){
        return this.channelname;
    }

    public String gettoken(){
        return this.token;
    }


}


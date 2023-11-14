package entity;

import com.pubnub.api.UserId;

public class User {
    private final String name;

    private final UserId userid;

    private final String password;

    public User(String name, UserId userid, String password){
        this.name = name;
        this.userid = userid;
        this.password = password;

    }

    public String getname(){
        return this.name;
    }

    public UserId getuserid(){
        return this.userid;
    }

    public String getPassword(){return this.password;}


}

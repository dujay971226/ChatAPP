package entity;

import com.pubnub.api.UserId;

public class User {
    private final String name;

    private final UserId userid;

    public User(String name, UserId userid, String createtime){
        this.name = name;
        this.userid = userid;

    }

    public String getname(){
        return this.name;
    }

    public UserId getuserid(){
        return this.userid;
    }


}

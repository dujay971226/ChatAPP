package entity;

public class User {
    private final String name;

    private final String userid;

    public User(String name, String userid, String createtime){
        this.name = name;
        this.userid = userid;

    }

    public String getname(){
        return this.name;
    }

    public String getuserid(){
        return this.userid;
    }


}

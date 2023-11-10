package interface_adapter.Profile;

import entity.User;

public class ProfileState {
    private String name = "";
    private String userid = "";
    public ProfileState(User user){
        this.name = user.getname();
        this.userid = user.getuserid();
    }
    public void setState(User user){
        this.name = user.getname();
        this.userid = user.getuserid();
    }
}

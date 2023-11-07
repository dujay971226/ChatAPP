package use_case.Profile;

import entity.User;

public class ProfileOutputData {
    final private User user;

    public ProfileOutputData(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}

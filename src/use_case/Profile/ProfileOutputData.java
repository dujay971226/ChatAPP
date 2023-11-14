package use_case.Profile;

import entity.User;

public class ProfileOutputData {
    final private User user;

    public ProfileOutputData(User user){
        this.user = user;
    }
    //create ---user + config
    //新outputdata user + channellist + config

    public User getUser(){
        return this.user;
    }
}

package data_access;

import entity.Channel;
import entity.User;

import java.util.ArrayList;

public interface iChannelDataAccessObject {
    public ArrayList<Channel> getChannels(User user);
    public void save(Channel channel, User curr, String jsonPath);
}

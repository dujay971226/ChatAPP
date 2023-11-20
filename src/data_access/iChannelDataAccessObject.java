package data_access;

import entity.Channel;
import entity.User;

import java.util.List;

public interface iChannelDataAccessObject {
    public List<Channel> getChannels(String username);
    public void add(Channel channel, User curr);
}

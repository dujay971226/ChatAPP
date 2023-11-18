package data_access;

import entity.Channel;

import java.util.List;

public interface ChannelDataAccessInterface {

    public List<Channel> getChannels(String username);
}

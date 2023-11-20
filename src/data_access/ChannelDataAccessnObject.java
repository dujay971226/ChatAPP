package data_access;

import com.google.gson.JsonObject;
import entity.Channel;
import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelDataAccessnObject implements iChannelDataAccessObject{
    private final JsonObject jsonFile;
    private final Map<Channel, User> accounts = new HashMap<>();

    public List<Channel> getChannels(String username);


}

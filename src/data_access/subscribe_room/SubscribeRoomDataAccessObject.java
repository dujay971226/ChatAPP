package data_access.subscribe_room;

import entity.Channel;
import entity.User;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;

// TODO finish upload to and pull from server
public class SubscribeRoomDataAccessObject implements SubscribeRoomDataAccessInterface {

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(String channelName) {

    }

    @Override
    public String getChannelNames() {
        return null;
    }
}

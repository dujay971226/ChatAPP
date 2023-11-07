package data_access.subscribe_room;

import entity.Channel;
import entity.User;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;

public class SubscribeRoomDataAccessObject implements SubscribeRoomDataAccessInterface {
    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(Channel channel) {

    }

    @Override
    public Channel get() {
        return null;
    }

}

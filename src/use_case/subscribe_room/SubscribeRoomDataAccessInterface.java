package use_case.subscribe_room;

import entity.Channel;
import entity.User;

public interface SubscribeRoomDataAccessInterface {
    boolean existsByName(String identifier);
    void save(Channel channel);
    Channel get();
}

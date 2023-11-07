package use_case.create_room;

import entity.Channel;
import entity.User;

public interface CreateRoomDataAcessInterface {
    boolean existsByName(String identifier);

    void save(Channel channel);

}

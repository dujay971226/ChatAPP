package data_access.create_room;

import entity.Channel;
import use_case.create_room.CreateRoomDataAcessInterface;

public class CreateRoomDataAccessObject implements CreateRoomDataAcessInterface {
    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(Channel channel) {

    }


}

package data_access.create_room;

import entity.Channel;
import use_case.create_room.CreateRoomDataAcessInterface;

// TODO finish upload channel to server
public class CreateRoomDataAccessObject implements CreateRoomDataAcessInterface {

    public CreateRoomDataAccessObject() {

    }
    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(String channelName) {

    }


}
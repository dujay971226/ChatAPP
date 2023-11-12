package data_access.subscribe_room;

import entity.Channel;
import entity.User;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;

// TODO finish upload to and pull from server
public class SubscribeRoomDataAccessObject implements SubscribeRoomDataAccessInterface {

    // for testing only --------------
    private final String[] channelNames = {"C1", "C2", "C3", "C4", "C5"};
    //--------------------------------

    @Override
    public boolean existsByName(String identifier) {
        for (String name : channelNames) {
            if (identifier.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(String channelName) {

    }

    @Override
    public String[] getChannelNames() {
        // TODO: write pubnub and get channel names
        // fake channel names below;
        return channelNames;
    }
}

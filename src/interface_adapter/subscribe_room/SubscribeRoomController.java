package interface_adapter.subscribe_room;

import use_case.subscribe_room.SubscribeRoomInputBoundary;

import java.awt.*;

public class SubscribeRoomController {

    final SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor;

    public SubscribeRoomController(SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor) {
        this.subscribeRoomUseCaseInteractor = subscribeRoomUseCaseInteractor;
    }

    public void execute(String channelName) {
        //
        //subscribeRoomUseCaseInteractor.execute();
    }

    public List getChannels() {
        return subscribeRoomUseCaseInteractor.getChannels();
    }


}

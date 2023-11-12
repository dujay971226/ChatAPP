package interface_adapter.subscribe_room;

import use_case.subscribe_room.SubscribeRoomInputBoundary;
import use_case.subscribe_room.SubscribeRoomInputData;

import java.awt.*;

public class SubscribeRoomController {

    final SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor;

    public SubscribeRoomController(SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor) {
        this.subscribeRoomUseCaseInteractor = subscribeRoomUseCaseInteractor;
    }

    public void execute(String channelName) {
        SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName);
        subscribeRoomUseCaseInteractor.execute(inputData);
    }

    public String[] getChannels() {
        return subscribeRoomUseCaseInteractor.getChannels();
    }


}

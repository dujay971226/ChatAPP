package interface_adapter.subscribe_room;

import use_case.subscribe_room.SubscribeRoomInputBoundary;
import use_case.subscribe_room.SubscribeRoomInputData;

import java.awt.*;

/**
 * Controller of subscribe room.
 */
public class SubscribeRoomController {

    final SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor;

    /**
     * Initializes a SubscribeRoomController instance.
     * @param subscribeRoomUseCaseInteractor subscribe room use case interactor
     */
    public SubscribeRoomController(SubscribeRoomInputBoundary subscribeRoomUseCaseInteractor) {
        this.subscribeRoomUseCaseInteractor = subscribeRoomUseCaseInteractor;
    }

    /**
     * Creates input data and interactor executes.
     * @param channelName channel name
     */
    public void execute(String channelName) {
        SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName);
        subscribeRoomUseCaseInteractor.execute(inputData);
    }

    /**
     * Gets channel information.
     * @return channel string array.
     */
    public String[] getChannels() {
        return subscribeRoomUseCaseInteractor.getChannels();
    }


}

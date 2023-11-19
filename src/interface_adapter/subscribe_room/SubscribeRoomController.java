package interface_adapter.subscribe_room;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.subscribe_room.SubscribeRoomInputBoundary;
import use_case.subscribe_room.SubscribeRoomInputData;

import java.awt.*;
import java.util.ArrayList;

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
    public void execute(String channelName, PubNub config, User user) {
        SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName, config, user);
        subscribeRoomUseCaseInteractor.execute(inputData);
    }


}

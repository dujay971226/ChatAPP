package interface_adapter.subscribe_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;
import use_case.subscribe_room.SubscribeRoomInputBoundary;
import use_case.subscribe_room.SubscribeRoomInputData;

import java.util.ArrayList;

/**
 * Controller of subscribe room.
 * @author huangzhihao
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
    public void execute(String channelName, PubNub config, User user, ArrayList<Channel> channelList) {
        SubscribeRoomInputData inputData = new SubscribeRoomInputData(channelName, config, user, channelList);
        subscribeRoomUseCaseInteractor.execute(inputData);
    }


}

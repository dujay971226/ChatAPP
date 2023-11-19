package use_case.subscribe_room;

import com.pubnub.api.PubNub;

import java.awt.*;
import java.util.ArrayList;

/**
 * Interactor of subscribe room.
 */
public class SubscribeRoomInteractor implements SubscribeRoomInputBoundary {

    final SubscribeRoomDataAccessInterface subscribeRoomDataAccessObject;
    final SubscribeRoomOutputBoundary subscribeRoomPresenter;

    /**
     * Initializes a SubscribeRoomInteractor instance.
     * @param subscribeRoomDataAccessObject data access object of subscribe room
     * @param subscribeRoomOutputBoundary presenter of subscribe room
     */
    public SubscribeRoomInteractor(SubscribeRoomDataAccessInterface subscribeRoomDataAccessObject,
                                   SubscribeRoomOutputBoundary subscribeRoomOutputBoundary) {
        this.subscribeRoomDataAccessObject = subscribeRoomDataAccessObject;
        this.subscribeRoomPresenter = subscribeRoomOutputBoundary;
    }

    /**
     * Executes by calling data access object.
     * @param subscribeRoomInputData input data
     */
    @Override
    public void execute(SubscribeRoomInputData subscribeRoomInputData) {
        PubNub pubNub = subscribeRoomInputData.getConfig();
        subscribeRoomDataAccessObject.getMessageLog(subscribeRoomInputData.getUser().getName(),
                subscribeRoomInputData.getChannelName());
    }

    /**
     * Gets channels using data access object.
     * @return channels.
     */
    @Override
    public ArrayList<String> getChannels() {
        return subscribeRoomDataAccessObject.getChannelNames();
    }


}

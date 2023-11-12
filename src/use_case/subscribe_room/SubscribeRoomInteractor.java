package use_case.subscribe_room;

import java.awt.*;

public class SubscribeRoomInteractor implements SubscribeRoomInputBoundary {

    final SubscribeRoomDataAccessInterface subscribeRoomDataAccessObject;
    final SubscribeRoomOutputBoundary subscribeRoomPresenter;

    public SubscribeRoomInteractor(SubscribeRoomDataAccessInterface subscribeRoomDataAccessObject,
                                   SubscribeRoomOutputBoundary subscribeRoomOutputBoundary) {
        this.subscribeRoomDataAccessObject = subscribeRoomDataAccessObject;
        this.subscribeRoomPresenter = subscribeRoomOutputBoundary;
    }


    @Override
    public void execute(SubscribeRoomInputData subscribeRoomInputData) {
        //
    }

    @Override
    public List getChannels() {
        String[] channelStrings = subscribeRoomDataAccessObject.getChannelNames();
        List lst = new List();
        for (String channel : channelStrings) {
            lst.add(channel);
        }
        return lst;
    }


}

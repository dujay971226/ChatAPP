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
        if (subscribeRoomDataAccessObject.existsByName(subscribeRoomInputData.getChannelName())) {

            // TODO: do something to save info (add user to channel?)
            SubscribeRoomOutputData subscribeRoomOutputData = new SubscribeRoomOutputData(
                    subscribeRoomInputData.getChannelName());
            subscribeRoomPresenter.prepareSuccessView(subscribeRoomOutputData);
        } else {
            subscribeRoomPresenter.prepareFailView("Channel not found");
        }
    }

    @Override
    public String[] getChannels() {
        return subscribeRoomDataAccessObject.getChannelNames();
    }


}

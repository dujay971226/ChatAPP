package interface_adapter.subscribe_room;


import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomOutputData;

public class SubscribeRoomPresenter implements SubscribeRoomOutputBoundary {

    private final SubscribeRoomViewModel SubscribeViewModel;

    public SubscribeRoomPresenter(SubscribeRoomViewModel subscribeRoomViewModel) {
        this.SubscribeViewModel = subscribeRoomViewModel;
    }


    @Override
    public void prepareSuccessView(SubscribeRoomOutputData outputData) {
        System.out.println("Successfully passed output data: " + outputData.getChannelName());

        // TODO
    }

    @Override
    public void prepareFailView(String error) {
        // TODO
    }
}

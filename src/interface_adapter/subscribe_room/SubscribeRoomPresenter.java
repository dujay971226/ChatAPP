package interface_adapter.subscribe_room;


import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomViewModel;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomOutputData;

public class SubscribeRoomPresenter implements SubscribeRoomOutputBoundary {

    private final SubscribeRoomViewModel subscribeViewModel;
    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;

    public SubscribeRoomPresenter(ViewManagerModel managerModel, SubscribeRoomViewModel subscribeRoomViewModel,
                                  RoomViewModel roomViewModel) {
        this.subscribeViewModel = subscribeRoomViewModel;
        this.roomViewModel = roomViewModel;
        this.viewManagerModel = managerModel;
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

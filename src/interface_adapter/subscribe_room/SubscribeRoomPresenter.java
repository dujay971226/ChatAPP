package interface_adapter.subscribe_room;


import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomViewModel;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomOutputData;

/**
 * Presenter of subscribe room.
 */
public class SubscribeRoomPresenter implements SubscribeRoomOutputBoundary {

    private final SubscribeRoomViewModel subscribeViewModel;
    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Initializes a subscribeRoomPresenter instance.
     * @param managerModel view manager
     * @param subscribeRoomViewModel subscribe room view model
     * @param roomViewModel room view model
     */
    public SubscribeRoomPresenter(ViewManagerModel managerModel, SubscribeRoomViewModel subscribeRoomViewModel,
                                  RoomViewModel roomViewModel) {
        this.subscribeViewModel = subscribeRoomViewModel;
        this.roomViewModel = roomViewModel;
        this.viewManagerModel = managerModel;
    }


    @Override
    public void prepareSuccessView(SubscribeRoomOutputData outputData) {

    }

    @Override
    public void prepareFailView(String error) {
        // TODO
    }
}

package interface_adapter.subscribe_room;


import entity.Channel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomOutputData;

/**
 * Presenter of subscribe room.
 * @author huangzhihao
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

    /**
     * Transitions to room view.
     * @param outputData output data.
     */
    @Override
    public void prepareSuccessView(SubscribeRoomOutputData outputData) {
        RoomState state = roomViewModel.getState();
        state.setChannel(new Channel(outputData.getChannelName(), outputData.getUser()));
        state.setConfig(outputData.getConfig());
        state.setUser(outputData.getUser());
        state.setMessageLog(outputData.getMessageLog());
        state.setNotice();
        roomViewModel.setState(state);
        roomViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Pop up with error message.
     * @param error error string
     */
    @Override
    public void prepareFailView(String error) {
        SubscribeRoomState state = subscribeViewModel.getState();
        state.setChannelNameError(error);
        subscribeViewModel.setState(state);
        subscribeViewModel.firePropertyChanged();
    }
}

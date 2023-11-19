package interface_adapter.create_room;

import entity.Channel;
import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

/**
 * Presenter of create room.
 */
public class CreateRoomPresenter implements CreateRoomOutputBoundary {
    private final CreateRoomViewModel createRoomViewModel;
    private ViewManagerModel viewManagerModel;
    private RoomViewModel roomViewModel;

    /**
     *
     * @param createRoomViewModel
     * @param roomViewModel
     */
    public CreateRoomPresenter(ViewManagerModel managerModel, CreateRoomViewModel createRoomViewModel, RoomViewModel roomViewModel) {
        this.viewManagerModel = managerModel;
        this.createRoomViewModel = createRoomViewModel;
        this.roomViewModel = roomViewModel;
    }

    /**
     * Prepares success view. Changes view to room.
     * @param outputData output data
     */
    @Override
    public void prepareSuccessView(CreateRoomOutputData outputData) {
        RoomState state = roomViewModel.getState();
        state.setChannel(new Channel(outputData.getChannelName()));
        state.setConfig(outputData.getConfig());
        state.setUser(outputData.getUser());
    }

    /**
     * Prepares Fail view. Show error.
     * @param error error message
     */
    @Override
    public void prepareFailView(String error) {
        CreateRoomState state = createRoomViewModel.getState();
        state.setChannelNameError(error);
        createRoomViewModel.firePropertyChanged();
    }
}

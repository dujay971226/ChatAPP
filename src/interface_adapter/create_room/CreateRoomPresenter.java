package interface_adapter.create_room;

import interface_adapter.ViewManagerModel;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

public class CreateRoomPresenter implements CreateRoomOutputBoundary {
    private final CreateRoomViewModel createRoomViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateRoomPresenter(CreateRoomViewModel createRoomViewModel) {
        this.createRoomViewModel = createRoomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateRoomOutputData channelName) {
        // TODO finish RoomViewModel
    }

    @Override
    public void prepareFailView(String error) {
        CreateRoomState state = createRoomViewModel.getState();
        state.setChannelNameError(error);
        createRoomViewModel.firePropertyChanged();
    }
}

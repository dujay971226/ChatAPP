package interface_adapter.create_room;

import entity.Channel;
import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

public class CreateRoomPresenter implements CreateRoomOutputBoundary {
    private final CreateRoomViewModel createRoomViewModel;
    private ViewManagerModel viewManagerModel;

    private RoomViewModel roomViewModel;

    public CreateRoomPresenter(CreateRoomViewModel createRoomViewModel, RoomViewModel roomViewModel) {
        this.createRoomViewModel = createRoomViewModel;
        this.roomViewModel = roomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateRoomOutputData outputData) {
        System.out.println("Successfully passed output data: " + outputData.getChannelName());
        RoomState state = roomViewModel.getState();
        state.setChannel(new Channel(outputData.getChannelName()));
        //state.setUser(user);
        // RoomState.setConfig()
        // setChannel() setUser()
        //
        // TODO send output data to room.
    }

    @Override
    public void prepareFailView(String error) {
        CreateRoomState state = createRoomViewModel.getState();
        //state.setChannelNameError(error);
        createRoomViewModel.firePropertyChanged();
    }
}

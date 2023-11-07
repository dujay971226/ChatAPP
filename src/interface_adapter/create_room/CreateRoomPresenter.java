package interface_adapter.create_room;

import interface_adapter.ViewManagerModel;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.create_room.CreateRoomOutputData;

public class CreateRoomPresenter implements CreateRoomOutputBoundary {
    private final CreateRoomViewModel joinRoomViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateRoomPresenter(CreateRoomViewModel joinRoomViewModel) {
        this.joinRoomViewModel = joinRoomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateRoomOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}

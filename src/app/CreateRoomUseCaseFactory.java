package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.room.RoomViewModel;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import view.CreateRoomView;
import view.ViewManager;

public class CreateRoomUseCaseFactory {

    private CreateRoomUseCaseFactory() {}; // No access

    public static CreateRoomView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel) {
        CreateRoomView createRoomView = new CreateRoomView()

        return null;
    }

    private static CreateRoomController createCreateRoomUseCase(ViewManagerModel viewManagerModel,
                                                                CreateRoomViewModel createRoomViewModel,
                                                                RoomViewModel roomViewModel) {
        CreateRoomOutputBoundary createRoomOutputBoundary = new CreateRoomPresenter(viewManagerModel,
                createRoomViewModel, roomViewModel);
        CreateRoomInputBoundary createRoomInputBoundary = new CreateRoomInteractor(createRoomOutputBoundary);
        CreateRoomController createRoomController = new CreateRoomController(createRoomInputBoundary);
        return createRoomController;
    }

}

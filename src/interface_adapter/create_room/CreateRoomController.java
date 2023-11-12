package interface_adapter.create_room;

import entity.User;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInputData;

public class CreateRoomController {
    final CreateRoomInputBoundary createRoomUseCaseInteractor;
    public CreateRoomController(CreateRoomInputBoundary createRoomUseCaseInteractor) {
        this.createRoomUseCaseInteractor = createRoomUseCaseInteractor;
    }

    public void execute(String channelName) {
        CreateRoomInputData joinRoomInputData = new CreateRoomInputData(channelName);
        createRoomUseCaseInteractor.execute(joinRoomInputData);
    }
}

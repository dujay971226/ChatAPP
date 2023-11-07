package interface_adapter.create_room;

import entity.User;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInputData;

public class CreateRoomController {
    final CreateRoomInputBoundary joinRoomUseCaseInteractor;
    public CreateRoomController(CreateRoomInputBoundary joinRoomUseCaseInteractor) {
        this.joinRoomUseCaseInteractor = joinRoomUseCaseInteractor;
    }

    public void execute(User user) {
        CreateRoomInputData joinRoomInputData = new CreateRoomInputData(user);
        joinRoomUseCaseInteractor.execute(joinRoomInputData);
    }
}

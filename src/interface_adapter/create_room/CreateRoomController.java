package interface_adapter.create_room;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInputData;

/**
 * Controller of create room.
 */
public class CreateRoomController {

    final CreateRoomInputBoundary createRoomUseCaseInteractor;

    /**
     * Initializes an instance of createRoomController with a given use case interactor.
     * @param createRoomUseCaseInteractor use case interactor of create room
     */
    public CreateRoomController(CreateRoomInputBoundary createRoomUseCaseInteractor) {
        this.createRoomUseCaseInteractor = createRoomUseCaseInteractor;
    }

    /**
     * Executes by creating input data and calling interactor.
     * @param channelName channel name
     * @param config instance of PubNub class
     * @param user user
     */
    public void execute(String channelName, PubNub config, User user) {
        CreateRoomInputData joinRoomInputData = new CreateRoomInputData(channelName, config, user);
        createRoomUseCaseInteractor.execute(joinRoomInputData);
    }
}

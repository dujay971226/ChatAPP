package interface_adapter.create_room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;
import use_case.create_room.CreateRoomInputBoundary;
import use_case.create_room.CreateRoomInputData;

import java.util.ArrayList;

/**
 * Controller of create room.
 *
 * @author huangzhihao
 */
public class CreateRoomController {

    final CreateRoomInputBoundary createRoomUseCaseInteractor;

    /**
     * Initializes an instance of createRoomController with a given use case interactor.
     *
     * @param createRoomUseCaseInteractor use case interactor of create room
     */
    public CreateRoomController(CreateRoomInputBoundary createRoomUseCaseInteractor) {
        this.createRoomUseCaseInteractor = createRoomUseCaseInteractor;
    }

    /**
     * Executes by creating input data and calling interactor.
     *
     * @param channelName channel name
     * @param config      instance of PubNub class
     * @param user        user
     * @param channelLog  past channel history
     */
    public void execute(String channelName, PubNub config, User user, ArrayList<Channel> channelLog) {
        CreateRoomInputData createRoomInputData = new CreateRoomInputData(channelName, config, user, channelLog);
        createRoomUseCaseInteractor.execute(createRoomInputData);
    }
}

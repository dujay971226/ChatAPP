package interface_adapter.room;

import com.pubnub.api.PubNub;
import entity.User;
import use_case.room.RoomExitInputData;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomMessageInputData;

public class RoomExitController {

    final RoomInputBoundary roomExitRoomUseCaseInteractor;
    public RoomExitController(RoomInputBoundary roomExitRoomUseCaseInteractor) {
        this.roomExitRoomUseCaseInteractor = roomExitRoomUseCaseInteractor;
    }
    public void execute(User user, PubNub config) {
        RoomExitInputData roomExitInputData = new RoomExitInputData(user, config);

        roomExitRoomUseCaseInteractor.execute(roomExitInputData);

    }
}

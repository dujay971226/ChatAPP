package interface_adapter.room.room_exit;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import entity.Channel;
import entity.User;
import use_case.room.room_exit.RoomExitInputBoundary;
import use_case.room.room_exit.RoomExitInputData;

public class RoomExitController {

    final RoomExitInputBoundary roomExitRoomUseCaseInteractor;
    public RoomExitController(RoomExitInputBoundary roomExitRoomUseCaseInteractor) {
        this.roomExitRoomUseCaseInteractor = roomExitRoomUseCaseInteractor;
    }

    //Send User Data and Pubnub Configuration to jump to Profile View.
    public void execute(User user, Channel channel, PubNub config, SubscribeCallback listener) {
        RoomExitInputData roomExitInputData = new RoomExitInputData(user, channel, config, listener);

        roomExitRoomUseCaseInteractor.execute(roomExitInputData);

    }
}

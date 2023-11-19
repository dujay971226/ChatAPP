package interface_adapter.room;

import com.pubnub.api.PubNub;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomMessageInputData;

public class RoomMessageController {

    final RoomInputBoundary roomSendMessageUseCaseInteractor;
    public RoomMessageController(RoomInputBoundary roomSendMessageUseCaseInteractor) {
        this.roomSendMessageUseCaseInteractor = roomSendMessageUseCaseInteractor;
    }
    public void execute(User user, Channel channel, PubNub config, String message) {
        RoomMessageInputData roomMessageInputData = new RoomMessageInputData(user, channel, config, message);
    }
}

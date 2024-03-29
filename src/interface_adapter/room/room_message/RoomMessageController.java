package interface_adapter.room.room_message;

import com.pubnub.api.PubNub;
import entity.Channel;
import use_case.room.room_message.RoomMessageInputBoundary;
import use_case.room.room_message.RoomMessageInputData;

public class RoomMessageController {

    final RoomMessageInputBoundary roomSendMessageUseCaseInteractor;

    public RoomMessageController(RoomMessageInputBoundary roomSendMessageUseCaseInteractor) {
        this.roomSendMessageUseCaseInteractor = roomSendMessageUseCaseInteractor;
    }

    //Pass the Message and relative information to interactor.
    public void execute(Channel channel, PubNub config, String message) {
        RoomMessageInputData roomMessageInputData = new RoomMessageInputData(channel, config, message);

        roomSendMessageUseCaseInteractor.execute(roomMessageInputData);
    }
}

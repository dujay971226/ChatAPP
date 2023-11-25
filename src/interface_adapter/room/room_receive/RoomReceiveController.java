package interface_adapter.room.room_receive;

import entity.Message;
import use_case.room.room_receive.RoomReceiveInputBoundary;
import use_case.room.room_receive.RoomReceiveInputData;

public class RoomReceiveController {

    final RoomReceiveInputBoundary roomReceiveMessageUseCaseInteractor;

    public RoomReceiveController(RoomReceiveInputBoundary roomReceiveMessageUseCaseInteractor) {
        this.roomReceiveMessageUseCaseInteractor = roomReceiveMessageUseCaseInteractor;
    }

    //Pass the received message for display
    public void execute(Message newMessage) {
        RoomReceiveInputData roomReceiveInputData = new RoomReceiveInputData(newMessage);

        roomReceiveMessageUseCaseInteractor.execute(roomReceiveInputData);
    }
}

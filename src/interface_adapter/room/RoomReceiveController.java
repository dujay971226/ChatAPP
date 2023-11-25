package interface_adapter.room;

import entity.Message;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomReceiveInputData;

import java.util.ArrayList;

public class RoomReceiveController {

    final RoomInputBoundary roomReceiveMessageUseCaseInteractor;

    public RoomReceiveController(RoomInputBoundary roomReceiveMessageUseCaseInteractor) {
        this.roomReceiveMessageUseCaseInteractor = roomReceiveMessageUseCaseInteractor;
    }

    public void execute(Message newMessage) {
        RoomReceiveInputData roomReceiveInputData = new RoomReceiveInputData(newMessage);

        roomReceiveMessageUseCaseInteractor.execute(roomReceiveInputData);
    }
}

package use_case.room.room_receive;

import entity.Message;

public class RoomReceiveInputData {
    private final Message message;

    public RoomReceiveInputData (Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

}

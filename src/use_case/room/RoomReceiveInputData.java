package use_case.room;

import entity.Message;

import java.util.ArrayList;

public class RoomReceiveInputData {
    private final Message message;

    public RoomReceiveInputData (Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

}

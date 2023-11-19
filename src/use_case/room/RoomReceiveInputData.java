package use_case.room;

import entity.Message;

import java.util.ArrayList;

public class RoomReceiveInputData {
    private final ArrayList<Message> messages;

    public RoomReceiveInputData (ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return this.messages;
    }

}

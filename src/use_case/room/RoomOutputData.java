package use_case.room;

import entity.Message;

import java.util.ArrayList;

public class RoomOutputData {

    private final Message newMessage;
    public RoomOutputData(Message newMessage) {
        this.newMessage = newMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }
}

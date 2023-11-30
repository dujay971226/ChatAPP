package use_case.room.room_receive;

import entity.Message;

public class RoomReceiveOutputData {

    private final Message newMessage;

    public RoomReceiveOutputData(Message newMessage) {
        this.newMessage = newMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }
}

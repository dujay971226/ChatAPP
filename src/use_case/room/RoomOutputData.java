package use_case.room;

import entity.Message;

import java.util.ArrayList;

public class RoomOutputData {

    private final ArrayList<Message> newMessageLog;
    public RoomOutputData(ArrayList<Message> newMessageLog) {
        this.newMessageLog = newMessageLog;
    }

    public ArrayList<Message> getNewMessageLog() {
        return newMessageLog;
    }
}

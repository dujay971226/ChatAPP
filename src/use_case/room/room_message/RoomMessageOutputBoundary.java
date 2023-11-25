package use_case.room.room_message;

public interface RoomMessageOutputBoundary {
    void prepareSentView();

    void prepareLostConnectionView();
}

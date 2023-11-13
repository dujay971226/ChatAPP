package interface_adapter.room;

import use_case.room.RoomInputBoundary;
import use_case.room.RoomFileInputData;

public class RoomFileController {

    final RoomInputBoundary roomFileUploadUseCaseInteractor;
    public RoomFileController(RoomInputBoundary roomFileUploadUseCaseInteractor) {
        this.roomFileUploadUseCaseInteractor = roomFileUploadUseCaseInteractor;
    }
    public void execute(String directory) {
        RoomFileInputData roomFileInputData = new RoomFileInputData(directory);
    }
}

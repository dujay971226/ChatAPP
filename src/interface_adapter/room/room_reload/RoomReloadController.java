package interface_adapter.room.room_reload;

import use_case.room.room_reload.RoomReloadInputBoundary;

public class RoomReloadController {
    final RoomReloadInputBoundary roomReloadUseCaseInteractor;

    public RoomReloadController(RoomReloadInputBoundary roomReloadUseCaseInteractor) {
        this.roomReloadUseCaseInteractor = roomReloadUseCaseInteractor;
    }

    //Pass the Message and relative information to interactor.
    public void execute() {

        roomReloadUseCaseInteractor.execute();
    }
}

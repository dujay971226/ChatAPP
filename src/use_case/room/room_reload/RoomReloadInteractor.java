package use_case.room.room_reload;

import use_case.room.room_receive.RoomReceiveOutputBoundary;

public class RoomReloadInteractor implements RoomReloadInputBoundary {

    final RoomReloadOutputBoundary roomReloadPresenter;

    public RoomReloadInteractor(RoomReloadOutputBoundary roomReloadOutputBoundary) {
        this.roomReloadPresenter = roomReloadOutputBoundary;
    }

    @Override
    public void execute() {

        roomReloadPresenter.prepareReloadView();
    }

}

package interface_adapter.room.room_reload;

import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.room.room_reload.RoomReloadOutputBoundary;

public class RoomReloadPresenter implements RoomReloadOutputBoundary {

    private final RoomViewModel roomViewModel;

    public RoomReloadPresenter(RoomViewModel roomViewModel) {
        this.roomViewModel = roomViewModel;
    }


    @Override
    public void prepareReloadView() {
        RoomState state = roomViewModel.getState();
        state.setNotice();
        roomViewModel.setState(state);
        roomViewModel.firePropertyChanged();
    }
}

package interface_adapter.room.room_receive;

import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.room.room_receive.RoomReceiveOutputBoundary;
import use_case.room.room_receive.RoomReceiveOutputData;

public class RoomReceivePresenter implements RoomReceiveOutputBoundary {
    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomReceivePresenter (ViewManagerModel viewManagerModel,
                                 RoomViewModel roomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roomViewModel = roomViewModel;
    }

    @Override
    public void prepareNewMessageView(RoomReceiveOutputData roomReceiveOutputData) {
        RoomState roomState = roomViewModel.getState();
        roomState.setMessage(roomReceiveOutputData.getNewMessage().getContent());
        roomState.setRecieveMessageNotice();
        roomViewModel.setState(roomState);
        roomViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(roomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

}

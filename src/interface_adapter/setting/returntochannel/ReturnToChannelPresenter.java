package interface_adapter.setting.returntochannel;

import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.setting.returntochannel.ReturnToChannelOutputBoundary;
import use_case.setting.returntochannel.ReturnToChannelOutputData;

public class ReturnToChannelPresenter implements ReturnToChannelOutputBoundary {
    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;

    public ReturnToChannelPresenter(RoomViewModel roomViewModel, ViewManagerModel viewManagerModel) {
        this.roomViewModel = roomViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ReturnToChannelOutputData returnToChannelOutputData) {
        RoomState state = roomViewModel.getState();

        state.setMessageLog(returnToChannelOutputData.getChannelHistory());
        state.setNotice();
        roomViewModel.setState(state);
        roomViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}

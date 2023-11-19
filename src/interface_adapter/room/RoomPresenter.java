package interface_adapter.room;

import interface_adapter.ViewManagerModel;
import use_case.room.RoomOutputBoundary;
import use_case.room.RoomOutputData;

import javax.swing.*;

public class RoomPresenter implements RoomOutputBoundary {

    private final RoomViewModel roomViewModel;
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomPresenter (ViewManagerModel viewManagerModel,
                          ProfileViewModel profileViewModel,
                          RoomViewModel roomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.roomViewModel = roomViewModel;

    }

    public void prepareProfileView() {

        this.viewManagerModel.setActiveView(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareNewMessageView(RoomOutputData roomOutputData) {
        RoomState roomState = roomViewModel.getState();
        roomState.setMessageLog(roomOutputData.getNewMessageLog());
        roomState.setNotice();
        roomViewModel.setState(roomState);
        roomViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(roomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    public void prepareLostConnectionView() {
        JOptionPane.showMessageDialog(
                null,
                "Message Send Failed. Please try again",
                "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
}

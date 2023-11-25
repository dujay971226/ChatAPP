package interface_adapter.room.room_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import use_case.room.room_message.RoomMessageOutputBoundary;

import javax.swing.*;

public class RoomMessagePresenter implements RoomMessageOutputBoundary {


    private final RoomViewModel roomViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomMessagePresenter (ViewManagerModel viewManagerModel,
                                   RoomViewModel roomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roomViewModel = roomViewModel;
    }


    //Set the input field to blank
    @Override
    public void prepareSentView() {
        RoomState roomState = roomViewModel.getState();
        roomState.setMessage("");
        roomViewModel.setState(roomState);
        roomViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(roomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    //If couldn't connect make a pop-up message to warn the error
    @Override
    public void prepareLostConnectionView() {
        JOptionPane.showMessageDialog(
                null,
                "Message Send Failed. Please try again",
                "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
}

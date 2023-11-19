package interface_adapter.room;

import use_case.room.RoomOutputBoundary;
import use_case.room.RoomOutputData;

import javax.swing.*;

public class RoomPresenter implements RoomOutputBoundary {

    public void prepareLostConnectionView() {
        JOptionPane.showMessageDialog(null, "Message Send Failed. Please try again", "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
}

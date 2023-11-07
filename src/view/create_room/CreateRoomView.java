package view.create_room;

import interface_adapter.create_room.CreateRoomViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private CreateRoomViewModel joinRoomViewModel;

    public CreateRoomView(CreateRoomViewModel joinRoomViewModel) {
        this.joinRoomViewModel = joinRoomViewModel;
        this.joinRoomViewModel.addPropetyChangedListener();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package view.create_room;

import interface_adapter.create_room.CreateRoomViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private CreateRoomViewModel createRoomViewModel;

    public CreateRoomView(CreateRoomViewModel createRoomViewModel) {
        this.createRoomViewModel = createRoomViewModel;
        this.createRoomViewModel.addPropertyChangeListener(this);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JTextField channelNameField = new JTextField(15);
        JButton createButton = new JButton("create");
        this.add(channelNameField);
        this.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

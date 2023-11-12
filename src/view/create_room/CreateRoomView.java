package view.create_room;

import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateRoomView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CreateRoomViewModel createRoomViewModel;
    private final JTextField createRoomTextField = new JTextField(15);
    private final CreateRoomController createRoomController;
    private final JButton createButton;

    public CreateRoomView(CreateRoomController controller, CreateRoomViewModel viewModel) {
        this.createRoomController = controller;
        this.createRoomViewModel = viewModel;
        createRoomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(createRoomViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel createRoomInfo = new LabelTextPanel(
                new JLabel(createRoomViewModel.CREATE_ROOM_LABEL), createRoomTextField);

        createButton = new JButton(createRoomViewModel.CREATE_BUTTON_LABEL);
        createButton.addActionListener(this);

        createRoomTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateRoomState currentState = createRoomViewModel.getState();
                currentState.setChannelName(createRoomTextField.getText());
                createRoomViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(createRoomInfo);
        this.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createButton)) {
            createRoomController.execute(createRoomTextField.getText());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateRoomState state = (CreateRoomState) evt.getNewValue();
        if (state.getChannelNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getChannelNameError());
        }
    }
}

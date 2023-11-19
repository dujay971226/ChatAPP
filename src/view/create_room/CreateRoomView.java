package view.create_room;

import interface_adapter.ProfileToSubscribeController;
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

/**
 * View shown when user is asked to create a room.
 */
public class CreateRoomView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CreateRoomViewModel createRoomViewModel;
    private final JTextField createRoomTextField = new JTextField(15);
    private final CreateRoomController createRoomController;
    private final ProfileToSubscribeController profileToSubscribeController;
    private final JButton createButton;
    private final JButton toSubscribeButton;

    /**
     * Creates a CreateRoomView instance.
     * @param controller controller of create room
     * @param viewModel view model of create room
     */
    public CreateRoomView(CreateRoomController controller, CreateRoomViewModel viewModel,
                          ProfileToSubscribeController profileToSubscribeController) {
        this.createRoomController = controller;
        this.createRoomViewModel = viewModel;
        this.profileToSubscribeController = profileToSubscribeController;
        createRoomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(createRoomViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel createRoomInfo = new LabelTextPanel(
                new JLabel(createRoomViewModel.CREATE_ROOM_LABEL), createRoomTextField);

        createButton = new JButton(createRoomViewModel.CREATE_BUTTON_LABEL);
        createButton.addActionListener(this);
        toSubscribeButton = new JButton(createRoomViewModel.TO_SUB_BUTTON_LABEL);
        toSubscribeButton.addActionListener(this);


        createRoomTextField.addKeyListener(new KeyListener() {

            /**
             * Changes channel name stored in create room state.
             * @param e the event to be processed
             */
            @Override
            public void keyTyped(KeyEvent e) {
                CreateRoomState currentState = createRoomViewModel.getState();
                currentState.setChannelName(createRoomTextField.getText() + e.getKeyChar());
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

    /**
     * Controller executes when button is clicked.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createButton)) {
            CreateRoomState currentState = createRoomViewModel.getState();
            createRoomController.execute(currentState.getChannelName(), currentState.getConfig(),
                    currentState.getUser());
        } else if (e.getSource().equals(toSubscribeButton)) {
            CreateRoomState currentState = createRoomViewModel.getState();
            profileToSubscribeController.execute(currentState.getUser(),
                    currentState.getConfig());
        }
    }

    /**
     * When called, checks if channel name error is not null. Shows error message if exists.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateRoomState state = createRoomViewModel.getState();
        if (state.getChannelNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getChannelNameError());
        }
    }
}

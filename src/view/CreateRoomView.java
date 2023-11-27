package view;

import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View shown when user is asked to create a room.
 * @author huangzhihao
 */
public class CreateRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * View name.
     */
    public final String viewName = "create";
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


        LabelTextPanel createRoomInfo = new LabelTextPanel(
                new JLabel(createRoomViewModel.CREATE_ROOM_LABEL), createRoomTextField);
        createRoomInfo.setBorder(new EmptyBorder(10, 0, 10, 0));
        createRoomInfo.setPreferredSize(new Dimension(300, 70));
        createButton = new JButton(createRoomViewModel.CREATE_BUTTON_LABEL);
        createButton.addActionListener(this);
        toSubscribeButton = new JButton(createRoomViewModel.TO_SUB_BUTTON_LABEL);
        toSubscribeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(toSubscribeButton);
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
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(createRoomInfo);
        this.add(buttonPanel);
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
                    currentState.getUser(), currentState.getChannelLog());
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

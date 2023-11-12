package view.subscribe_room;

import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubscribeRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final SubscribeRoomController subscribeRoomController;
    private final JScrollPane channelPane;
    private final JButton subscribeButton;

    public SubscribeRoomView(SubscribeRoomController controller, SubscribeRoomViewModel viewModel) {
        this.subscribeRoomController = controller;
        this.subscribeRoomViewModel = viewModel;
        subscribeRoomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(subscribeRoomViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        channelPane = new JScrollPane(subscribeRoomController.getChannels());
        channelPane.setAlignmentX(LEFT_ALIGNMENT);


        subscribeButton = new JButton(subscribeRoomViewModel.SUBSCRIBE_BUTTON_LABEL);
        subscribeButton.addActionListener(this);

        channelPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SubscribeRoomState currentState = subscribeRoomViewModel.getState();
                currentState.setChannelName(channelPane.getName());
                subscribeRoomViewModel.setState(currentState);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(channelPane);
        this.add(subscribeButton);


    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SubscribeRoomState state = (SubscribeRoomState) evt.getNewValue();
        if (state.getChannelNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getChannelNameError());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(subscribeButton)) {
            subscribeRoomController.execute(channelPane.getName());
        }
    }
}

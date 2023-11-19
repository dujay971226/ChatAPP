package view.subscribe_room;

import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View shown when user is asked to join a room.
 */
public class SubscribeRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final SubscribeRoomController subscribeRoomController;
    private final String[] channelNames;
    private final JList<String> channelList;
    private final JScrollPane channelPane;
    private final JButton subscribeButton;

    /**
     * Initializes a SubscribeRoomView instance.
     * @param controller controller of subscribe room
     * @param viewModel view model of subscribe room
     */
    public SubscribeRoomView(SubscribeRoomController controller, SubscribeRoomViewModel viewModel) {
        this.subscribeRoomController = controller;
        this.subscribeRoomViewModel = viewModel;
        subscribeRoomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(subscribeRoomViewModel.TITLE_LABEL);

        channelNames = subscribeRoomController.getChannels();

        channelList = new JList<String>(channelNames);
        channelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        channelList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            /**
             * Gets the selection and record it in state.
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    ListSelectionModel selectionModel = (ListSelectionModel) e.getSource();
                    int min = selectionModel.getMinSelectionIndex();
                    int max = selectionModel.getMaxSelectionIndex();
                    for (int i = min; i <= max; i++) {
                        if (selectionModel.isSelectedIndex(i)) {
                            SubscribeRoomState currentState = subscribeRoomViewModel.getState();
                            currentState.setChannelName(channelNames[i]);
                            subscribeRoomViewModel.setState(currentState);
                            break; // due to single selection
                        }
                    }
                }
            }
        });
        channelPane = new JScrollPane(channelList);

        subscribeButton = new JButton(subscribeRoomViewModel.SUBSCRIBE_BUTTON_LABEL);
        subscribeButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(channelPane);
        this.add(subscribeButton);


    }


    // property change not used
    @Override
    public void propertyChange(PropertyChangeEvent evt) {}

    /**
     * When button is clicked, controller executes according to the selection made. If one selection was recorded,
     * then prompts user to make one.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(subscribeButton)) {
            if (subscribeRoomViewModel.getState().getChannelName().isEmpty()) { // no selection
                JOptionPane.showMessageDialog(this,
                        subscribeRoomViewModel.getState().getNoSelectionMsg());
            } else {
                subscribeRoomController.execute(subscribeRoomViewModel.getState().getChannelName());
            }
        }
    }
}

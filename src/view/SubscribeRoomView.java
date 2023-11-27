package view;

import entity.Channel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;
import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * View shown when user is asked to join a room.
 * @author huangzhihao
 */
public class SubscribeRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * View name.
     */
    public final String viewName = "subscribe";
    private final SubscribeRoomViewModel subscribeRoomViewModel;
    private final SubscribeRoomController subscribeRoomController;
    private final ProfileToCreateController profileToCreateController;
    private final String[] channelNames;
    private final JButton subscribeButton;
    private final JButton toCreateButton;

    /**
     * Initializes a SubscribeRoomView instance.
     * @param controller controller of subscribe room
     * @param viewModel view model of subscribe room
     */
    public SubscribeRoomView(SubscribeRoomController controller, SubscribeRoomViewModel viewModel,
                             ProfileToCreateController profileToCreateController) {
        this.subscribeRoomController = controller;
        this.subscribeRoomViewModel = viewModel;
        this.profileToCreateController = profileToCreateController;
        subscribeRoomViewModel.addPropertyChangeListener(this);

        JLabel title1 = new JLabel(subscribeRoomViewModel.LIST_LABEL);
        title1.setBorder(new EmptyBorder(10, 0, 10, 0));

        SubscribeRoomState currentState = subscribeRoomViewModel.getState();
        ArrayList<Channel> channelLog = currentState.getChannelLog();
        ArrayList<String> channelStrings = new ArrayList<>();
        if (channelLog != null) {
            for (Channel channel : channelLog) {
                channelStrings.add(channel.getName());
            }
        }
        channelNames = channelStrings.toArray(new String[0]);
        JList<String> channelList = new JList<>(channelNames);
        channelList.setFont(channelList.getFont().deriveFont(13.0f));
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
        JScrollPane channelPane = new JScrollPane(channelList);
        channelPane.setPreferredSize(new Dimension(400, 150));
        JTextField search = new JTextField(15);
        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SubscribeRoomState currentState = subscribeRoomViewModel.getState();
                currentState.setChannelName(search.getText() + e.getKeyChar());
                subscribeRoomViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        LabelTextPanel searchPanel = new LabelTextPanel(new JLabel(subscribeRoomViewModel.TF_LABEL), search);
        searchPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JPanel channelPanel = new JPanel();
        channelPanel.setLayout(new BoxLayout(channelPanel, BoxLayout.PAGE_AXIS));
        channelPanel.add(title1);
        channelPanel.add(channelPane);
        channelPanel.add(searchPanel);

        subscribeButton = new JButton(subscribeRoomViewModel.SUBSCRIBE_BUTTON_LABEL);
        subscribeButton.addActionListener(this);
        toCreateButton = new JButton(subscribeRoomViewModel.TO_CREATE_BUTTON_LABEL);
        toCreateButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(subscribeButton);
        buttonPanel.add(toCreateButton);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(channelPanel);
        this.add(buttonPanel);

    }

    /**
     * Shows error message if exists.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SubscribeRoomState state = subscribeRoomViewModel.getState();
        if (state.getChannelNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getChannelNameError());
        }
    }

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
                subscribeRoomController.execute(subscribeRoomViewModel.getState().getChannelName(),
                        subscribeRoomViewModel.getState().getConfig(),
                        subscribeRoomViewModel.getState().getUser(),
                        subscribeRoomViewModel.getState().getChannelLog());
            }
        } else if (e.getSource().equals(toCreateButton)) {
            SubscribeRoomState state = subscribeRoomViewModel.getState();
            profileToCreateController.execute(state.getUser(), state.getConfig());
        }
    }
}

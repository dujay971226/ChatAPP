package view;

import entity.Message;
import interface_adapter.setting.deletemessage.DeleteMessageController;
import interface_adapter.setting.returntosetting.ReturnToSettingController;
import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class ChannelHistoryView extends JPanel implements ActionListener, PropertyChangeListener {


    public final String viewName = "channel history";
    private final JButton cancel;
    private final JButton delete;
    private JButton deleteAll;
    private final ArrayList<JButton> deleteMessageButtons = new ArrayList<>();
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ShowChannelHistoryController showChannelHistoryController;
    private final ReturnToSettingController returnToSettingController;
    private final DeleteMessageController deleteMessageController;
    private final JLabel channelMessageErrorField = new JLabel();
    private final JLabel deleteMessageErrorField = new JLabel();

    /**
     * A window with a title and a JButton.
     */
    public ChannelHistoryView(ChannelHistoryViewModel channelHistoryViewModel, ShowChannelHistoryController showChannelHistoryController, ReturnToSettingController returnToSettingController, DeleteMessageController deleteMessageController) {
        // Initialize the ChannelHistoryView with controllers and a view model
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.showChannelHistoryController = showChannelHistoryController;
        this.returnToSettingController = returnToSettingController;
        this.deleteMessageController = deleteMessageController;
        this.channelHistoryViewModel.addPropertyChangeListener(this);

        // Set the layout of the view to a vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create and center-align a title label
        JLabel title = new JLabel("Channel History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configure inner box layout and add it to the main view
        JPanel innerBox = new JPanel();
        innerBox.setLayout(new BoxLayout(innerBox, BoxLayout.X_AXIS));

        // Set up an inner panel for channel messages and configure its layout
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create scroll panes for innerPanel and deletePanel
        JScrollPane innerScrollPane = new JScrollPane(innerPanel);

        // Set up buttons panel and add cancel button with ActionListener
        JPanel buttons = new JPanel();
        cancel = new JButton(ChannelHistoryViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        // ActionListener for cancel button handling
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    ChannelHistoryState state = channelHistoryViewModel.getState(); state.setDeleteMessages(new HashMap<>());
                    returnToSettingController.execute(state.getChannelMessages());
                }
            }
        });

        // Set up delete buttons and confirmation dialog
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new GridLayout(0, 1));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane deteleScrollPane = new JScrollPane(deletePanel);
        deleteAll = new JButton("delete all of them!");
        delete = new JButton("delete");

        // ActionListener for delete button handling, showing a confirmation dialog
        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChannelHistoryState state = channelHistoryViewModel.getState();
                HashMap<Long, Message> deleteMessage = state.getDeleteMessages();
                Object[] timeTokens = deleteMessage.keySet().toArray();
                deleteMessageController.execute(timeTokens, deleteMessage, state.getChannel(), state.getConfig());
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Delete Message");

                // Create a JPanel
                JPanel panel = new JPanel();

                // Add a label to the JPanel
                JLabel label = new JLabel("Do you really want to delete these messages?");

                JButton no = new JButton("no");

                deleteAll.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }

                });
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                panel.add(label);
                panel.add(deleteAll);
                panel.add(no);

                // Add the JPanel to the JFrame
                frame.add(panel);

                // Set the size of the JFrame
                frame.setSize(300, 200);

                // Set the location of the popup window to the center of the screen
                frame.setLocationRelativeTo(null);

                // Set the JFrame to exit on close
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Make the JFrame visible
                frame.setVisible(true);
            }
        });

        // Add buttons to the buttons panel
        buttons.add(delete);

        innerBox.add(innerScrollPane);
        innerBox.add(deteleScrollPane);

        // Add title, innerBox, and buttons to the main view
        this.add(title);
        this.add(innerBox);
        this.add(channelMessageErrorField);
        this.add(deleteMessageErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes in the channel history view model
        ChannelHistoryState state = (ChannelHistoryState) evt.getNewValue();
        // this case load the rooms
        if (state.isActive()) {
            state.setActive(false);
            showChannelHistoryController.execute(state.getChannel(), state.getConfig());
        } else if (state.getChannelMessageError() != null) {
            channelMessageErrorField.setText(state.getChannelMessageError());
            state.setChannelMessageError(null);
        } else if (state.getDeleteMessageError() != null) {
            deleteMessageErrorField.setText(state.getDeleteMessageError());
            state.setDeleteMessageError(null);
            JPanel innerBox = (JPanel) this.getComponent(1);
            JScrollPane deleteScrollPanel = (JScrollPane) innerBox.getComponent(1);
            JPanel deleteMessagePanel = (JPanel) deleteScrollPanel.getViewport().getView();
            reloadDeleteMessagePanel(deleteMessagePanel);
        } else {
            JPanel innerBox = (JPanel) this.getComponent(1);
            JScrollPane innerScrollPanel = (JScrollPane) innerBox.getComponent(0);
            JPanel innerPanel = (JPanel) innerScrollPanel.getViewport().getView();
            JScrollPane deleteScrollPanel = (JScrollPane) innerBox.getComponent(1);
            JPanel deleteMessagePanel = (JPanel) deleteScrollPanel.getViewport().getView();

            if (state.isUpdateDelete()) {
                state.setUpdateDelete(false);
                reloadDeleteMessagePanel(deleteMessagePanel);
                showChannelHistoryController.execute(state.getChannel(), state.getConfig());
            } else {
                innerPanel.removeAll();
                ArrayList<Message> channelMessages = state.getChannelMessages();
                if (!channelMessages.isEmpty()) {
                    for (int i = 0; i < channelMessages.size(); i++) {
                        Message messageItem = channelMessages.get(i);
                        JPanel messagePanel = new JPanel();

                        JLabel mLabel = new JLabel(messageItem.toString());
                        mLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                        messagePanel.add(mLabel);

                        if (messageItem.getUser().getName().equals(state.getCurrentUser().getName())) {
                            JButton addToDelete = new JButton("X");
                            addToDelete.setAlignmentX(Component.RIGHT_ALIGNMENT);
                            addToDelete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    HashMap<Long, Message> deleteMessages = state.getDeleteMessages();
                                    deleteMessages.put(messageItem.getStartTimeStamp(), messageItem);
                                    reloadDeleteMessagePanel(deleteMessagePanel);
                                }
                            });

                            deleteMessageButtons.add(addToDelete);
                            messagePanel.add(addToDelete);
                        }

                        innerPanel.add(messagePanel);
                    }
                } else {
                    innerPanel.add(new JLabel("There's no channel history till now!"));
                }
                try {
                    sleep(100);
                } catch (InterruptedException ignored) {
                }
                innerPanel.revalidate();
                innerPanel.repaint();
            }
        }
    }

    // Reload the delete message panel based on the updated state
    private void reloadDeleteMessagePanel(JPanel deleteMessagePanel) {
        deleteMessagePanel.removeAll();
        HashMap<Long, Message> deleteMessage = this.channelHistoryViewModel.getState().getDeleteMessages();
        for (Long timestamp : deleteMessage.keySet()) {
            Message message = deleteMessage.get(timestamp);
            JLabel mLabel = new JLabel(message.toString());
            mLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            deleteMessagePanel.add(mLabel);
        }
        try {
            sleep(100);
        } catch (InterruptedException ignored) {
        }
        deleteMessagePanel.revalidate();
        deleteMessagePanel.repaint();
    }

    // Simulate cancel button press action
    public void simulateCancelButtonPress() {
        ActionEvent actionEvent = new ActionEvent(cancel, ActionEvent.ACTION_PERFORMED, "CancelButtonPressed");
        ActionListener[] actionListeners = cancel.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
        }
    }

    // Simulate delete buttons press action
    public void simulateDeleteButtonsPress() {
        ActionEvent actionEvent = new ActionEvent(delete, ActionEvent.ACTION_PERFORMED, "CancelButtonPressed");
        ActionListener[] actionListeners = delete.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
        }
        ActionEvent actionEvent2 = new ActionEvent(deleteAll, ActionEvent.ACTION_PERFORMED, "CancelButtonPressed");
        ActionListener[] actionListeners2 = deleteAll.getActionListeners();
        for (ActionListener listener2 : actionListeners2) {
            listener2.actionPerformed(actionEvent2);
        }
    }
}

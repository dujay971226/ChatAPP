package view;


import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import entity.Message;
import entity.User;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import interface_adapter.room.room_exit.RoomExitController;
import interface_adapter.room.room_message.RoomMessageController;
import interface_adapter.room.room_receive.RoomReceiveController;
import interface_adapter.room.room_reload.RoomReloadController;
import interface_adapter.room.room_to_journal.RoomToJournalController;
import interface_adapter.room.room_to_setting.RoomToSettingController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Thread.sleep;

public class RoomView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "room";

    private final RoomViewModel roomViewModel;
    private final JTextArea messageInputField = new JTextArea(3, 30);
    private final RoomMessageController roomMessageController;
    private final RoomReceiveController roomReceiveController;
    private final RoomExitController roomExitController;
    private final RoomReloadController roomReloadController;
    private final RoomToSettingController roomToSettingController;
    private final RoomToJournalController roomToJournalController;
    private final JButton setting;
    private final JButton journal;
    private final JButton exit;
    private final JButton send;
    private final JButton reload;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> messageList = new JList<String>(listModel);


    public RoomView(RoomMessageController roomMessageController,
                    RoomReceiveController roomReceiveController,
                    RoomExitController roomExitController,
                    RoomReloadController roomReloadController,
                    RoomViewModel roomViewModel,
                    RoomToSettingController roomToSettingController,
                    RoomToJournalController roomToJournalController) throws PubNubException {
        this.roomMessageController = roomMessageController;
        this.roomReceiveController = roomReceiveController;
        this.roomExitController = roomExitController;
        this.roomReloadController = roomReloadController;
        this.roomViewModel = roomViewModel;
        this.roomToSettingController = roomToSettingController;
        this.roomToJournalController = roomToJournalController;
        roomViewModel.addPropertyChangeListener(this);

        //Name of the Room
        JPanel top = new JPanel();
        top.setAlignmentX(CENTER_ALIGNMENT);
        JLabel title = new JLabel(RoomViewModel.TITLE_LABEL);
        title.setFont(messageList.getFont().deriveFont(20.0f));
        top.add(title);

        //buttons to swap to journal view, setting view and profile view
        JPanel buttons_high = new JPanel();
        buttons_high.setAlignmentX(CENTER_ALIGNMENT);
        reload = new JButton(RoomViewModel.RELOAD_BUTTON_LABEL);
        buttons_high.add(reload);
        journal = new JButton(RoomViewModel.JOURNAL_BUTTON_LABEL);
        buttons_high.add(journal);
        setting = new JButton(RoomViewModel.SETTING_BUTTON_LABEL);
        buttons_high.add(setting);
        exit = new JButton(RoomViewModel.EXIT_BUTTON_LABEL);
        buttons_high.add(exit);

        //Panel with scroll bar to display message
        messageList.setFont(messageList.getFont().deriveFont(18.0f));
        JScrollPane roomPane = new JScrollPane(messageList);
        roomPane.setPreferredSize(new Dimension(800, 600));

        //Panel to Input Message and send message
        JPanel low = new JPanel();
        low.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageInputField.setAlignmentX(Component.LEFT_ALIGNMENT);
        low.add(messageInputField);
        send = new JButton(RoomViewModel.SEND_BUTTON_LABEL);
        low.add(send);

        RoomState currState = roomViewModel.getState();


        //Check whether send button was clicked
        send.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) {
                            RoomState currState = roomViewModel.getState();
                            String text = currState.getUser().getName() + ": " + messageInputField.getText();
                            messageInputField.setText("");
                            roomMessageController.execute(
                                    currState.getUser(),
                                    currState.getChannel(),
                                    currState.getConfig(),
                                    text
                            );
                        }
                    }
                }
        );

        reload.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(reload)) {
                            RoomState currState = roomViewModel.getState();
                            roomReloadController.execute();
                        }
                    }
                }
        );

        //Check whether setting button was clicked
        setting.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(setting)) {
                            RoomState currState = roomViewModel.getState();
                            roomToSettingController.execute(
                                    currState.getUser(),
                                    currState.getChannel(),
                                    currState.getConfig()
                            );
                        }
                    }
                }
        );

        //Check whether journal button was clicked
        journal.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(journal)) {
                            roomToJournalController.execute();
                        }
                    }
                }
        );

        //Check whether exit button was clicked
        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exit)) {
                            RoomState currState = roomViewModel.getState();
                            String text = messageInputField.getText();
                            roomExitController.execute(
                                    currState.getUser(),
                                    currState.getChannel(),
                                    currState.getConfig(),
                                    currState.getListener()
                            );
                        }
                    }
                }
        );

        //Update typed in information and check whether enter key was pressed
        messageInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            messageInputField.setText("");
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                            RoomState currState = roomViewModel.getState();
                            String text = currState.getUser().getName() + ": " + messageInputField.getText();
                            messageInputField.setText("");
                            roomMessageController.execute(
                                    currState.getUser(),
                                    currState.getChannel(),
                                    currState.getConfig(),
                                    text
                            );
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        messageInputField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int keyCode = e.getKeyCode();

                        if (e.isShiftDown() && keyCode == KeyEvent.VK_ENTER) {
                            String text = messageInputField.getText();
                            messageInputField.setText(text + "\n ");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(top);
        this.add(buttons_high);
        this.add(roomPane);
        this.add(low);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RoomState currState = roomViewModel.getState();
        //The user joined a new room.
        if (currState.getNEW_ROOM_UPDATE()) {
            PubNub pubnub = currState.getConfig();
            SubscribeCallback listener = new SubscribeCallback() {

                @Override
                public void status(PubNub pubnub, PNStatus status) {
                    if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {

                    } else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {

                    } else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
                        // Happens as part of our regular operation. This event happens when
                        // radio / connectivity is lost, then regained.
                    } else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
                        // Handle message decryption error. Probably client configured to
                        // encrypt messages and on live data feed it received plain text.
                    }
                }

                @Override
                public void message(PubNub pubnub, PNMessageResult message) {
                    // Handle new message stored in message.message

                    String msg = message.getMessage().getAsJsonObject().get("msg").getAsString();
                    User user = currState.getUser();
                    Message newMessages = new Message(user, msg, LocalDateTime.now());
                    roomReceiveController.execute(newMessages);

                    /*
                     * Log the following items with your favorite logger - message.getMessage() -
                     * message.getSubscription() - message.getTimetoken()
                     */
                }

                @Override
                public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {

                }

                @Override
                public void uuid(PubNub pubnub, PNUUIDMetadataResult pnUUIDMetadataResult) {

                }

                @Override
                public void channel(@NotNull PubNub pubNub, @NotNull PNChannelMetadataResult pnChannelMetadataResult) {

                }

                @Override
                public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {

                }

                @Override
                public void messageAction(PubNub pubnub, PNMessageActionResult pnMessageActionResult) {

                }

                @Override
                public void file(@NotNull PubNub pubNub, @NotNull PNFileEventResult pnFileEventResult) {

                }

                @Override
                public void presence(PubNub pubnub, PNPresenceEventResult presence) {

                }
            };

            currState.setListener(listener);
            currState.setOffNEW_ROOM_UPDATE();
            pubnub.addListener(listener);
        }

        //Someone updated the message log, need to load the history message
        if (currState.getLOG_UPDATE()) {
            listModel.removeAllElements();
            ArrayList<Message> newMessages = currState.getMessageLog();
            int i = 0;
            while (i < 5 & newMessages == null) {
                try {
                    sleep(100);
                } catch (InterruptedException ignored) {
                }
                i++;
            }
            ArrayList<String> sortedMessage = SortByDate(newMessages);
            for (String msg : sortedMessage) {
                listModel.addElement(msg);
            }

            currState.setOffNotice();
            scrollToBottom();

            //Someone sent a message online, need to load
        }
        if (currState.getNEW_MESSAGE_UPDATE()) {
            listModel.addElement(currState.getMessage());
            currState.setMessage("");
            currState.setOffReceiveMessageNotice();
            messageInputField.setText("");
            scrollToBottom();
        }

//        messageList.revalidate();
//        messageList.repaint();
    }


    private ArrayList<String> SortByDate(ArrayList<Message> newMessages) {
        Map<LocalDateTime, String> dateFormatMap = new TreeMap<>();
        newMessages.forEach(s -> dateFormatMap.put(s.getTime(), s.getContent()));
        return new ArrayList<>(dateFormatMap.values());
    }

    private void scrollToBottom() {
        int lastIndex = messageList.getModel().getSize() - 1;
        if (lastIndex >= 0) {
            messageList.ensureIndexIsVisible(lastIndex);
        }
    }


}

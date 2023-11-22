package view;


import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.PubNubException;
import entity.Message;
import entity.User;
import interface_adapter.room.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RoomView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "room";

    private final RoomViewModel roomViewModel;
    private final JTextField messageInputField;
    private final RoomMessageController roomMessageController;
    private final RoomReceiveController roomReceiveController;
    private final RoomExitController roomExitController;
    private final RoomToSettingController roomToSettingController;
    private final RoomToJournalController roomToJournalController;
    private final JButton setting;
    private final JButton journal;
    private final JButton exit;
    private final JButton send;



    public RoomView (RoomMessageController roomMessageController,
                     RoomReceiveController roomReceiveController,
                     RoomExitController roomExitController,
                     RoomViewModel roomViewModel,
                     RoomToSettingController roomToSettingController,
                     RoomToJournalController roomToJournalController) throws PubNubException  {
        this.roomMessageController = roomMessageController;
        this.roomReceiveController = roomReceiveController;
        this.roomExitController = roomExitController;
        this.roomViewModel = roomViewModel;
        this.roomToSettingController = roomToSettingController;
        this.roomToJournalController = roomToJournalController;
        roomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RoomViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons_high = new JPanel();
        buttons_high.setAlignmentX(Component.RIGHT_ALIGNMENT);
        journal = new JButton(RoomViewModel.JOURNAL_BUTTON_LABEL);
        buttons_high.add(journal);
        setting = new JButton(RoomViewModel.SETTING_BUTTON_LABEL);
        buttons_high.add(setting);
        exit = new JButton(RoomViewModel.EXIT_BUTTON_LABEL);
        buttons_high.add(exit);

        RoomState currState = roomViewModel.getState();
        ArrayList<Message> newMessages = currState.getMessageLog();
        ArrayList<String>sortedMessage = SortByDate(newMessages);
        String[] sortedMessageStringList = sortedMessage.toArray(new String[0]);
        JList<String> messageList = new JList<String>(sortedMessageStringList);
        messageList.setFont(messageList.getFont().deriveFont(14.0f));
        JScrollPane roomPane = new JScrollPane(messageList);
        roomPane.setPreferredSize(new Dimension(800,600));

        JPanel low = new JPanel();
        low.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageInputField = new JTextField();
        low.add(messageInputField);
        send = new JButton(RoomViewModel.SEND_BUTTON_LABEL);
        low.add(send);


        send.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) {
                            RoomState currState = roomViewModel.getState();
                            String text = messageInputField.getText();
                            currState.setMessage("");
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

        journal.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(journal)) {
                            roomToJournalController.execute();
                        }
                    }
                }
        );

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exit)) {
                            RoomState currState = roomViewModel.getState();
                            String text = messageInputField.getText();
                            roomExitController.execute(
                                    currState.getUser(),
                                    currState.getConfig()
                            );
                        }
                    }
                }
        );

        messageInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RoomState currentState = roomViewModel.getState();
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            RoomState currState = roomViewModel.getState();
                            String text = messageInputField.getText();
                            currentState.setMessage("");
                            roomMessageController.execute(
                                    currState.getUser(),
                                    currState.getChannel(),
                                    currState.getConfig(),
                                    text
                            );
                        }
                        else {
                            String text = messageInputField.getText() + e.getKeyChar();
                            currentState.setMessage(text);
                            roomViewModel.setState(currentState);
                        }

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


    }


    public void MessageReceiver(String[] args) throws PubNubException {

        RoomState currState = roomViewModel.getState();

        PubNub pubnub = currState.getConfig();
        String channelName = currState.getChannel().getName();


        pubnub.addListener(new SubscribeCallback() {

            private boolean isConnected;

            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                    // This event happens when radio / connectivity is lost
                } else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
                    // Connect event. You can do stuff like publish, and know you'll get it.
                    // Or just use the connected event to confirm you are subscribed for
                    // UI / internal notifications, etc
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
                if (message.getChannel() != null) {
                    // Message has been received on channel group stored in
                    // message.getChannel()
                } else {
                    // Message has been received on channel stored in
                    // message.getSubscription()
                }

                JsonElement receivedMessageObject = message.getMessage();
                JsonElement receivedUserObject = message.getUserMetadata().getAsJsonObject().get("");
                User user = currState.getUser();
                String msg = receivedMessageObject.toString();
                ArrayList<Message> newMessages = currState.getMessageLog();
                newMessages.add(new Message(user, msg, LocalDateTime.now()));
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
        });

        pubnub.subscribe()
                .channels(Collections.singletonList(channelName))
                .execute();    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    private ArrayList<String> SortByDate(ArrayList<Message> newMessages) {
        Map<LocalDateTime, String> dateFormatMap = new TreeMap<>();
        newMessages.forEach(s -> dateFormatMap.put(s.getTime(), s.getUser().getName() + s.getContent()));
        return new ArrayList<>(dateFormatMap.values());
    }



}

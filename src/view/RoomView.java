package view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

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
import interface_adapter.room.RoomFileController;
import interface_adapter.room.RoomMessageController;
import interface_adapter.room.RoomState;
import interface_adapter.room.RoomViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RoomView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Room";

    private final RoomViewModel roomViewModel;
    private final JTextArea messaageDisplayField = new JTextArea(100,50);
    private final JScrollPane scrollPane = new JScrollPane(messaageDisplayField);
    private final JTextArea messageInputField = new JTextArea(20,50);
    private final RoomMessageController roomMessageController;
    private final RoomFileController roomFileController;
    private final SettingController settingController;
    private final JournalController journalController;
    private final JButton setting;
    private final JButton journal;
    private final JButton send;
    private final JButton upload;



    public RoomView (RoomMessageController roomMessageController, RoomFileController roomFileController, RoomViewModel roomViewModel, SettingController settingController, JournalController journalController)   {
        this.roomMessageController = roomMessageController;
        this.roomFileController = roomFileController;
        this.roomViewModel = roomViewModel;
        this.settingController = settingController;
        this.journalController = journalController;
        roomViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RoomViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons_high = new JPanel();
        buttons_high.setAlignmentX(Component.RIGHT_ALIGNMENT);
        setting = new JButton(RoomViewModel.SETTING_BUTTON_LABEL);
        buttons_high.add(setting);
        journal = new JButton(RoomViewModel.JOURNAL_BUTTON_LABEL);
        buttons_high.add(journal);

        JTextArea chatInfo = messaageDisplayField;
        JTextArea messageInputInfo = messageInputField;

        JPanel buttons_low = new JPanel();
        buttons_low.setAlignmentX(Component.RIGHT_ALIGNMENT);
        upload = new JButton(RoomViewModel.UPLOAD_BUTTON_LABEL);
        buttons_low.add(upload);
        send = new JButton(RoomViewModel.SEND_BUTTON_LABEL);
        buttons_low.add(send);


        send.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) {
                            RoomState currentState = roomViewModel.getState();
                            roomMessageController.execute(
                                    currentState.getMessage(),
                                    currentState.getChannel(),
                                    currentState.getUser(),
                            );
                        }
                    }
                }
        );

        upload.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upload)) {
                            String directory = JOptionPane.showInputDialog(new JFrame("Upload File"),
                                    "What is the directory of the file you would like to upload", null);
                            roomFileController.execute(directory);
                        }
                    }
                }
        );

        setting.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(setting)) {
                            settingController.execute();
                        }
                    }
                }
        );

        journal.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(journal)) {
                            journalController.execute();
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
                            roomMessageController.execute(
                                    currentState.getMessage()
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

        public void displayMessage(User user, Message msg) {
            String chat = messageInputField.getText() + e.getKeyChar();
            currentState.setMessage(text);
            roomViewModel.setState(currentState);
        }
    }
    public static void main(String[] args) throws PubNubException {
        final UserId userId = new UserId("myUniqueUserId");
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");

        PubNub pubnub = new PubNub(pnConfiguration);

        final String channelName = "myChannel";

        // create message payload using Gson
        final JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", "Connection established");


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
                    pubnub.publish()
                            .channel(channelName)
                            .message(messageJsonObject)
                            .async((result, publishStatus) -> {
                                if (!publishStatus.isError()) {
                                    this.isConnected = true;
                                }
                                // Request processing failed.
                                else {
                                    System.out.println("Not Connected");
                                }
                            });
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
                System.out.println("Received message: " + receivedMessageObject.toString());
                // extract desired parts of the payload, using Gson
                String msg = message.getMessage().getAsJsonObject().get("msg").getAsString();
                System.out.println("The content of the message is: " + msg);

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
}

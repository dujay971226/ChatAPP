package view;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import interface_adapter.setting.deletemessage.DeleteMessageController;
import interface_adapter.setting.returntosetting.ReturnToSettingController;
import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ChannelHistoryView extends JPanel implements ActionListener, PropertyChangeListener {


    public final String viewName = "channel history";
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ReturnToSettingController returnToSettingController;
    private final DeleteMessageController deleteMessageController;
    private final JLabel channelMessageErrorField = new JLabel();
    final JButton cancel;

    /**
     * A window with a title and a JButton.
     */
    public ChannelHistoryView(ChannelHistoryViewModel channelHistoryViewModel, ReturnToSettingController returnToSettingController, DeleteMessageController deleteMessageController) {
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.returnToSettingController = returnToSettingController;
        this.deleteMessageController = deleteMessageController;
        this.channelHistoryViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Channel History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane innerScrollPane = new JScrollPane(innerPanel);

        JPanel buttons = new JPanel();
        cancel = new JButton(ChannelHistoryViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    returnToSettingController.execute();
                }
            }
        });

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new GridLayout(0, 1));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane deteleScrollPane = new JScrollPane(deletePanel);
        JButton detele = new JButton("delete");
        detele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Delete Message");

                // Create a JPanel
                JPanel panel = new JPanel();

                // Add a label to the JPanel
                JLabel label = new JLabel("Do you really want to delete these messages?");
                JButton yesE = new JButton("delete each of them!");
                yesE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChannelHistoryState state = channelHistoryViewModel.getState();
                        Long[] timeTokens = (Long[]) state.getDeleteMessages().values().toArray();
                        deleteMessageController.execute(timeTokens, state.);
                        frame.dispose();
                    }
                });
                JButton yesA = new JButton("delete all messages in between too!");
                yesA.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChannelHistoryState state = channelHistoryViewModel.getState();
                        Object[] timeTokens = state.getDeleteMessages().values().toArray();
                        deleteMessageController.execute();
                        frame.dispose();
                    }
                });
                JButton no = new JButton("no");
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                panel.add(label);
                panel.add(yesE);
                panel.add(yesA);
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
        buttons.add(detele);

        this.add(title);
        this.add(innerScrollPane);
        this.add(deteleScrollPane);
        this.add(channelMessageErrorField);
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
        ChannelHistoryState state = (ChannelHistoryState) evt.getNewValue();

        if (state.getChannelMessageError() != null){
            channelMessageErrorField.setText(state.getChannelMessageError());
            state.setChannelMessageError(null);
        } else {
            JScrollPane innerScrollPanel = (JScrollPane) this.getComponent(1);
            JPanel innerPanel = (JPanel) innerScrollPanel.getViewport().getView();
            JScrollPane deleteScrollPanel = (JScrollPane) this.getComponent(2);
            JPanel deletePanel = (JPanel) deleteScrollPanel.getViewport().getView();

            innerPanel.removeAll();

            List<PNFetchMessageItem> channelMessages = state.getChannelMessages();
            if (channelMessages != null){
                for (PNFetchMessageItem messageItem : channelMessages) {
                    JPanel messagePanel = new JPanel();
                    long time = messageItem.getTimetoken() / 10000000L;
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                            TimeZone.getDefault().toZoneId());
                    String message = localDateTime + "     ";
                    message += messageItem.getMessage().getAsJsonObject().get("msg");
                    JLabel mLabel = new JLabel(message);
                    mLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    JButton button = new JButton("X");
                    button.setAlignmentX(Component.RIGHT_ALIGNMENT);
                    String finalMessage = message;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            deletePanel.removeAll();


                        }
                    });
                    messagePanel.add(mLabel);
                    messagePanel.add(button);
                    innerPanel.add(messagePanel);
                }
            } else{
                innerPanel.add(new JLabel("There's no channel history till now!"));
            }

            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }
}

package view;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
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
import java.util.List;
import java.util.TimeZone;

public class ChannelHistoryView extends JPanel implements ActionListener, PropertyChangeListener {


    public final String viewName = "channel history";
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ReturnToSettingController returnToSettingController;
    private final JLabel channelMessageErrorField = new JLabel();
    final JButton cancel;

    /**
     * A window with a title and a JButton.
     */
    public ChannelHistoryView(ChannelHistoryViewModel channelHistoryViewModel, ReturnToSettingController returnToSettingController) {
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.returnToSettingController = returnToSettingController;
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(innerScrollPane);
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

            innerPanel.removeAll();

            List<PNFetchMessageItem> channelMessages = state.getChannelMessages();
            if (channelMessages != null){
                for (PNFetchMessageItem messageItem : channelMessages) {
                    long time = messageItem.getTimetoken() / 10000000L;
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                            TimeZone.getDefault().toZoneId());
                    String message = localDateTime + "     ";
                    message += messageItem.getMessage().getAsJsonObject().get("msg");
                    innerPanel.add(new JLabel(message));
                }
            } else{
                innerPanel.add(new JLabel("There's no channel history till now!"));
            }

            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }
}

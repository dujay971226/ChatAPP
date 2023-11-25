package view;

import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
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
import java.util.HashMap;
import java.util.List;

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
            List<PNFetchMessageItem> channelMessages = state.getChannelMessages();
            for (PNFetchMessageItem messageItem : channelMessages) {
                System.out.println(messageItem.getMessage());
                System.out.println(messageItem.getMeta());
                System.out.println(messageItem.getTimetoken());
                System.out.println(messageItem.getUuid());
                HashMap<String, HashMap<String, List<PNFetchMessageItem.Action>>> actions =
                        messageItem.getActions();
                for (String type : actions.keySet()) {
                    System.out.println("Action type: " + type);
                    for (String value : actions.get(type).keySet()) {
                        System.out.println("Action value: " + value);
                        for (PNFetchMessageItem.Action action : actions.get(type).get(value)) {
                            System.out.println("Action timetoken: " + action.getActionTimetoken());
                            System.out.println("Action publisher: " + action.getUuid());
                        }
                    }
                }
            }
        }
    }
}

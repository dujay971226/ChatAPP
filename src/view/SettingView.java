package view;

import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import interface_adapter.showsetting.SettingState;
import interface_adapter.showsetting.SettingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

public class SettingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "channel history";
    private final SettingViewModel settingViewModel;

    private final JLabel channelMessageErrorField = new JLabel();
    private final JLabel loadingSubcribersErrorField = new JLabel();
    final JButton cancel;
    final JButton channelhistory;
    final JButton messagefilter;

    public SettingView(SettingViewModel settingViewModel){
        this.settingViewModel = settingViewModel;
        this.settingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Channel History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        cancel = new JButton(SettingViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        channelhistory = new JButton(SettingViewModel.CHANNEL_HISTORY_BUTTON_LABEL);
        buttons.add(channelhistory);
        messagefilter = new JButton(SettingViewModel.MESSAGE_FILTER_BUTTON_LABEL);
        buttons.add(messagefilter);


        cancel.addActionListener(this);
        channelhistory.addActionListener(this);
        messagefilter.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(channelMessageErrorField);
        this.add(loadingSubcribersErrorField);
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
        SettingState state = (SettingState) evt.getNewValue();

        Collection<PNHereNowChannelData> channelOccupancy = state.getChannelOccupancy();
        for (PNHereNowChannelData channelData : channelOccupancy) {
            System.out.println("---");
            System.out.println("channel:" + channelData.getChannelName());
            System.out.println("occupancy: " + channelData.getOccupancy());
            System.out.println("occupants:");
            for (PNHereNowOccupantData occupant : channelData.getOccupants()) {
                System.out.println("uuid: " + occupant.getUuid() + " state: " + occupant.getState());
            }
        }
    }


}

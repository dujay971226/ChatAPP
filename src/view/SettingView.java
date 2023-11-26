package view;

import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import interface_adapter.setting.returntochannel.ReturnToChannelController;
import interface_adapter.setting.showchannelhistory.ShowChannelHistoryController;
import interface_adapter.setting.showsetting.SettingState;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.setting.showsetting.ShowSettingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

public class SettingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "channel setting";
    private final SettingViewModel settingViewModel;
    private final ShowSettingController showSettingController;
    private final ShowChannelHistoryController showChannelHistoryController;
    private final ReturnToChannelController returnToChannelController;
    private final JLabel loadingSubcribersErrorField = new JLabel();
    final JButton cancel;
    final JButton channelhistory;

    public SettingView(SettingViewModel settingViewModel, ShowSettingController showSettingController, ShowChannelHistoryController showChannelHistoryController, ReturnToChannelController returnToChannelController){
        this.settingViewModel = settingViewModel;
        this.showSettingController = showSettingController;
        this.showChannelHistoryController = showChannelHistoryController;
        this.returnToChannelController = returnToChannelController;
        this.settingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Channel History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        cancel = new JButton(SettingViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    returnToChannelController.execute();
                }
            }
        });
        buttons.add(cancel);
        channelhistory = new JButton(SettingViewModel.CHANNEL_HISTORY_BUTTON_LABEL);
        channelhistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(channelhistory)) {
                    SettingState state = settingViewModel.getState();
                    showChannelHistoryController.execute(state.getChannel().getName(), state.getConfig());
                }
            }
        });
        buttons.add(channelhistory);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(loadingSubcribersErrorField);
        this.add(buttons);
    }
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingState state = (SettingState) evt.getNewValue();
        if(state.isActive()){
            state.setActiveState(false);
            showSettingController.execute(state.getChannel().getName(), state.getConfig());
        } else if (state.getLoadingSubscribersError() != null) {
            loadingSubcribersErrorField.setText(state.getLoadingSubscribersError());
            state.setLoadingSubscribersError(null);
        } else {
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


}

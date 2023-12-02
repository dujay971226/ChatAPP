package view;

import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import interface_adapter.setting.returntochannel.ReturnToChannelController;
import interface_adapter.setting.settingtochannelhistory.SettingToChannelHistoryController;
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

import static java.lang.Thread.sleep;

public class SettingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "channel setting";
    final JButton cancel;
    final JButton channelhistory;
    private final SettingViewModel settingViewModel;
    private final ShowSettingController showSettingController;
    private final SettingToChannelHistoryController settingToChannelHistoryController;
    private final ReturnToChannelController returnToChannelController;

    // Initialize the SettingView with controllers and a view model
    public SettingView(SettingViewModel settingViewModel, ShowSettingController showSettingController, SettingToChannelHistoryController settingToChannelHistoryController, ReturnToChannelController returnToChannelController) {
        // Set the view model and controllers for the SettingView
        this.settingViewModel = settingViewModel;
        this.showSettingController = showSettingController;
        this.settingToChannelHistoryController = settingToChannelHistoryController;
        this.returnToChannelController = returnToChannelController;

        // Register the SettingView as a property change listener for the view model
        this.settingViewModel.addPropertyChangeListener(this);
        // Add a label for online users to the SettingView
        this.add(new JLabel("Current Online Users"));

        // Set up inner panel with a grid layout and add it to a scroll pane
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 5));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane innerScrollPane = new JScrollPane(innerPanel);

        // Add the inner scorllable JPanel to the SettingView JPanel
        this.add(innerScrollPane, BorderLayout.CENTER);

        // Set up a button panel with cancel and channel history buttons
        JPanel buttons = new JPanel();
        cancel = new JButton(SettingViewModel.CANCEL_BUTTON_LABEL);
        // ActionListener for cancel button handling
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    SettingState state = settingViewModel.getState();
                    returnToChannelController.execute(state.getChannelHistory());
                }
            }
        });
        // Add buttons to the button panel
        buttons.add(cancel);
        channelhistory = new JButton(SettingViewModel.CHANNEL_HISTORY_BUTTON_LABEL);
        // ActionListener for channel history button handling
        channelhistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(channelhistory)) {
                    SettingState state = settingViewModel.getState();
                    settingToChannelHistoryController.execute(state.getChannel().getName(), state.getConfig(), state.getUser());
                }
            }
        });
        buttons.add(channelhistory);
        // Configure the overall layout of the SettingView as a vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add loading label to the SettingView
        this.add(buttons);
        JLabel loading = new JLabel("loading user...");
        innerPanel.add(loading);

        // Wait for the async methods to finish
        try {
            sleep(300);
        } catch (InterruptedException ignored) {
        }
        // Refresh the inner panel
        innerPanel.revalidate();
        innerPanel.repaint();
    }

    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingState state = (SettingState) evt.getNewValue();
        if (state.isActive()) {
            state.setActiveState(false);
            showSettingController.execute(state.getChannel().getName(), state.getConfig());
        }
        else if (state.getLoadingSubscribersError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoadingSubscribersError());
            state.setLoadingSubscribersError(null);
        } else {
            JScrollPane innerScrollPanel = (JScrollPane) this.getComponent(1);
            JPanel innerPanel = (JPanel) innerScrollPanel.getViewport().getView();

            innerPanel.removeAll();

            Collection<PNHereNowChannelData> channelOccupancy = state.getChannelOccupancy();
            for (PNHereNowChannelData channelData : channelOccupancy) {
                for (PNHereNowOccupantData occupant : channelData.getOccupants()) {
                    innerPanel.add(new JLabel(occupant.getUuid()));
                }
            }

            try {
                sleep(100);
            } catch (InterruptedException ignored) {
            }

            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }

    // Simulate cancel button press action
    public void simulateCancelButtonPress() {
        ActionEvent actionEvent = new ActionEvent(cancel, ActionEvent.ACTION_PERFORMED, "CancelButtonPressed");
        ActionListener[] actionListeners = cancel.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
        }
    }

    // Simulate channel history button press action
    public void simulateChannelHistoryButtonPress() {
        ActionEvent actionEvent = new ActionEvent(channelhistory, ActionEvent.ACTION_PERFORMED, "ChannelHistoryButtonPressed");
        ActionListener[] actionListeners = channelhistory.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
        }
    }


}

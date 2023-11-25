package interface_adapter.showsetting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingViewModel extends ViewModel {
    public final String TITLE_LABEL = "Channel Setting";
    public static final String CHANNEL_HISTORY_BUTTON_LABEL = "Channel History";
    public static final String MESSAGE_FILTER_BUTTON_LABEL = "Message Filter";
    public static final String CANCEL_BUTTON_LABEL = "Back";

    private SettingState state = new SettingState();



    public SettingViewModel() {
        super("channel setting");
    }

    public SettingState getState() {
        return state;
    }

    public void setState(SettingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}

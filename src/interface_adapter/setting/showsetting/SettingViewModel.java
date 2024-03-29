package interface_adapter.setting.showsetting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingViewModel extends ViewModel {
    public static final String CHANNEL_HISTORY_BUTTON_LABEL = "Channel History";
    public static final String CANCEL_BUTTON_LABEL = "Back";
    public final String TITLE_LABEL = "Channel Setting";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
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

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}

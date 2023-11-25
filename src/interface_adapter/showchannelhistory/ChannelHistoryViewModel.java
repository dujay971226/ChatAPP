package interface_adapter.showchannelhistory;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChannelHistoryViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Channel History View";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private ChannelHistoryState state = new ChannelHistoryState();

    public ChannelHistoryViewModel(String viewName) {
        super(viewName);
    }

    public ChannelHistoryState getState() {
        return state;
    }

    public void setState(ChannelHistoryState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

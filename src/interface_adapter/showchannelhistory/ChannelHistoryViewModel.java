package interface_adapter.showchannelhistory;

import interface_adapter.ViewModel;
import interface_adapter.showsetting.SettingState;

import java.beans.PropertyChangeListener;

public class ChannelHistoryViewModel extends ViewModel {

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

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}

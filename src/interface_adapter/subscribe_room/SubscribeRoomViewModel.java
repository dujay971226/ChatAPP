package interface_adapter.subscribe_room;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SubscribeRoomViewModel extends ViewModel {

    public final String TITLE_LABEL = "Join a room";
    public final String SUBSCRIBE_BUTTON_LABEL = "Join";

    private SubscribeRoomState state = new SubscribeRoomState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SubscribeRoomViewModel() {
        super("subscribe room");
    }

    public void setState(SubscribeRoomState state) {
        this.state = state;
    }

    public SubscribeRoomState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

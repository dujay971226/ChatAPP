package interface_adapter.subscribe_room;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model of subscribe room.
 */
public class SubscribeRoomViewModel extends ViewModel {

    /**
     * Title label.
     */
    public final String TITLE_LABEL = "Join a room";
    /**
     * Button label.
     */
    public final String SUBSCRIBE_BUTTON_LABEL = "Join";

    private SubscribeRoomState state = new SubscribeRoomState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes a SubscribeRoomViewModel instance and calls super class instructor.
     */
    public SubscribeRoomViewModel() {
        super("subscribe room");
    }

    /**
     * Sets state.
     * @param state current state
     */
    public void setState(SubscribeRoomState state) {
        this.state = state;
    }

    /**
     * Gets state.
     * @return state of view model
     */
    public SubscribeRoomState getState() {
        return state;
    }

    /**
     * Fires property changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds property change listener.
     * @param listener property change listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

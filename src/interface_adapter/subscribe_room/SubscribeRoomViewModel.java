package interface_adapter.subscribe_room;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model of subscribe room.
 * @author huangzhihao
 */
public class SubscribeRoomViewModel extends ViewModel {

    /**
     * Title label.
     */
    public static final String TITLE_LABEL = "subscribe";
    public final String LIST_LABEL = "Join a room by choosing from the list below and click \"join\":";
    public final String TF_LABEL = "Or enter room name here:";

    /**
     * Button label.
     */
    public final String SUBSCRIBE_BUTTON_LABEL = "Join";

    /**
     * To create view button label.
     */
    public final String TO_CREATE_BUTTON_LABEL = "Create Instead";

    private SubscribeRoomState state = new SubscribeRoomState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes a SubscribeRoomViewModel instance and calls super class instructor.
     */
    public SubscribeRoomViewModel() {
        super("subscribe");
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

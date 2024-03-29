package interface_adapter.create_room;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model of create room.
 *
 * @author huangzhihao
 */
public class CreateRoomViewModel extends ViewModel {

    public static final String TITLE_LABEL = "create";
    /**
     * Label of text field.
     */
    public final String CREATE_ROOM_LABEL = "Enter room name here to create a room:";
    /**
     * Label of button.
     */
    public final String CREATE_BUTTON_LABEL = "Create";
    /**
     * Label of subscribe button
     */
    public final String TO_SUB_BUTTON_LABEL = "Join Instead";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CreateRoomState state = new CreateRoomState();


    /**
     * Creates an instance of CreateRoomViewModel and calls super class instructor.
     */
    public CreateRoomViewModel() {
        super("create");
    }

    /**
     * Gets the state.
     *
     * @return state of create room view model
     */
    public CreateRoomState getState() {
        return state;
    }

    /**
     * Updates the state.
     *
     * @param state state of create room view model
     */
    public void setState(CreateRoomState state) {
        this.state = state;
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
     *
     * @param listener property change listener.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

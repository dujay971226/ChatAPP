package interface_adapter.create_room;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateRoomViewModel extends ViewModel {

    public final String TITLE_LABEL = "Create a room";

    public final String CREATE_ROOM_LABEL = "Enter Room Name";

    public final String CREATE_BUTTON_LABEL = "Create";

    private CreateRoomState state = new CreateRoomState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public CreateRoomViewModel() {
        super("create room");
    }

    public void setState(CreateRoomState state) {
        this.state = state;
    }

    public CreateRoomState getState() {
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

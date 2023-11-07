package interface_adapter.create_room;

import interface_adapter.ViewModel;
import view.create_room.CreateRoomView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateRoomViewModel extends ViewModel {
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

package interface_adapter.room;

import com.pubnub.api.PubNubException;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RoomViewModel extends ViewModel {

    public static final String JOURNAL_BUTTON_LABEL = "Journal";
    public static final String SETTING_BUTTON_LABEL = "Setting";
    public static final String EXIT_BUTTON_LABEL = "Exit";
    public static final String RELOAD_BUTTON_LABEL = "Reload";
    public static String TITLE_LABEL = "Chat Room";
    public static final String UPLOAD_BUTTON_LABEL = "Upload File";
    public static final String SEND_BUTTON_LABEL = "Send";
    private RoomState state = new RoomState();

    public RoomViewModel() throws PubNubException {
        super("room");
    }

    public void setState(RoomState state) {
        this.state = state;
        TITLE_LABEL = state.getChannel().getName();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RoomState getState() {
        return state;
    }

}

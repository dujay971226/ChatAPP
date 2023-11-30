package interface_adapter.journal;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the journal view in the application.
 * This class extends ViewModel and manages the state of the journal view, including
 * handling property changes and updating the view based on different search operations.
 *
 * @author Xiaofeng Li
 */
public class JournalViewModel extends ViewModel {
    public static final String SEARCH_KEY_WORD_LABEL = "Searching by keyword";
    public static final String SEARCH_DOI_LABEL = "Searching by doi";
    public static final String SEARCH_AUTHOR_LABEL = "Searching author";
    public static final String BACK_BUTTON_LABEL = "Back";
    final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JournalState state = new JournalState();

    /**
     * Constructs a JournalViewModel with the default view name set to "journal".
     */
    public JournalViewModel() {
        super("journal");
    }

    /**
     * Retrieves the current state of the journal view.
     *
     * @return The current JournalState.
     */
    public JournalState getState() {
        return this.state;
    }

    /**
     * Sets the state of the journal view to the specified JournalState.
     *
     * @param state The new state to be set for the journal view.
     */
    public void setState(JournalState state) {
        this.state = state;
    }

    /**
     * Adds a property change listener to this model.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event when the state of the journal view changes.
     * This method notifies all registered listeners of the change.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}

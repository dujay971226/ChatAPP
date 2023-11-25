package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * The abstract ViewModel class serves as a base class for specific view models.
 * It contains common functionality for managing the view name and notifying listeners of property changes.
 */
public abstract class ViewModel {

    private final String viewName;

    /**
     * Constructs a ViewModel with the specified view name.
     *
     * @param viewName The name of the associated view.
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the name of the associated view.
     *
     * @return The name of the associated view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Notifies registered listeners about changes in the ViewModel.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
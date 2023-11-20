package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * Abstract class for view models.
 */
public abstract class ViewModel {

    private final String viewName;

    /**
     * Initializes a ViewModel instance.
     * @param viewName name of view
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets view name.
     * @return view name.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Fires property changed.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds property change listener.
     * @param listener property change listener.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}

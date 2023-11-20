package interface_adapter.Journal;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JournalViewModel extends ViewModel {
    final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JournalState state;
    public static String SEARCH_KEY_WORD_LABEL = "Searching by keyword";
    public static String SEARCH_DOI_LABEL = "Searching by doi";
    public static String SEARCH_ISSN_LABEL = "Searching by issn";

    public static String BACK_BUTTON_LABEL = "Back";
    public JournalViewModel(){super("journal");}
    public void setState(JournalState state){this.state = state;}
    public JournalState getState(){return this.state;}

    public void addPropertyChangeListener(PropertyChangeListener listener){

    }
    public void firePropertyChanged(){support.firePropertyChange("state",null,this.state);}
}

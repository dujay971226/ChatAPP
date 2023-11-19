package interface_adapter.Journal;

import java.beans.PropertyChangeSupport;

public class JournalViewModel extends{
    final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public static String SEARCH_KEY_WORD_LABEL = "Searching by keyword";
    public static String SEARCH_DOI_LABEL = "Searching by doi";

    public static String SEARCH_BUTTON_LABEL = "Search";
    public static String BACK_BUTTON_LABEL = "Back";
    public JournalViewModel()

}

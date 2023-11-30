package interface_adapter.journal;

/**
 * Represents the state of a journal in the application.
 * This class holds data related to the current state of journal-related operations,
 * such as search results.
 *
 * @author Xiaofeng Li
 */
public class JournalState {
    private String searchResult = "";

    /**
     * Constructs a new JournalState instance with default values.
     */
    public JournalState() {
    }

    /**
     * Retrieves the current search result string.
     *
     * @return A string representing the current search result.
     */
    public String getSearchResult() {
        return searchResult;
    }

    /**
     * Sets the search result string to the specified value.
     *
     * @param searchResult The new search result string.
     */
    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }
}


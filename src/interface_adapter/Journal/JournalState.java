package interface_adapter.Journal;

public class JournalState {
    private String searchResult = "";
    public JournalState(){}

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }
}

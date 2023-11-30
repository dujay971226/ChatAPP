package use_case.journal.Doi;

/**
 * The JournalDoiOutputData class represents the output data for journal operations based on Digital Object Identifiers (DOIs).
 * It contains the result of the DOI-based journal operation.
 *
 * @author Xiaofeng Li
 */
public class JournalDoiOutputData {
    private final String result;

    /**
     * Constructs a JournalDoiOutputData object with the specified result.
     *
     * @param result The result of the DOI-based journal operation.
     */
    public JournalDoiOutputData(String result) {
        this.result = result;
    }

    /**
     * Gets the result of the DOI-based journal operation.
     *
     * @return The result of the DOI-based journal operation.
     */
    public String getResult() {
        return result;
    }
}


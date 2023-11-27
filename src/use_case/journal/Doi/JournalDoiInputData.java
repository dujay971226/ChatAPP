package use_case.journal.Doi;

/**
 * The JournalDoiInputData class represents input data for executing journal DOI-related operations.
 * It contains the DOI (Digital Object Identifier) information required for the operation.
 *
 * @author Xiaofeng Li
 */
public class JournalDoiInputData {

    private String doi;

    /**
     * Constructs a JournalDoiInputData object with the provided DOI.
     *
     * @param doi The Digital Object Identifier (DOI) for the journal operation.
     */
    public JournalDoiInputData(String doi) {
        this.doi = doi;
    }

    /**
     * Get the DOI (Digital Object Identifier) associated with this input data.
     *
     * @return The DOI for the journal operation.
     */
    public String getDoi() {
        return doi;
    }
}


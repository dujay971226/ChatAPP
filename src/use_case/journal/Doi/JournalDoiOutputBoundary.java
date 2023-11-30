package use_case.journal.Doi;

/**
 * The JournalDoiOutputBoundary interface defines the contract for preparing a success view
 * for journal operations based on Digital Object Identifiers (DOIs).
 * Implementing classes should provide an implementation for preparing the view based on the output data.
 *
 * @author Xiaofeng Li
 */
public interface JournalDoiOutputBoundary {

    /**
     * Prepares a success view for a journal operation based on a DOI with the provided output data.
     * Implementing classes should define how the view is prepared based on the specific requirements.
     *
     * @param journalDoiOutputData The output data containing the result of the DOI-based journal operation.
     */
    void prepareSuccessView(JournalDoiOutputData journalDoiOutputData);
}


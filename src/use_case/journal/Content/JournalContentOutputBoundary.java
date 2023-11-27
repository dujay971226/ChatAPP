package use_case.journal.Content;

/**
 * This interface defines the output boundary for journal content-related operations.
 * Classes that implement this interface are responsible for preparing and communicating
 * success views based on the results of journal content operations.
 *
 * @author Xiaofeng Li
 */
public interface JournalContentOutputBoundary {

    /**
     * Prepares a success view based on the provided journal content output data.
     * Implementing classes should use the data contained in the output data object to
     * construct and display the appropriate view to the user, presenting the results
     * of a journal content operation.
     *
     * @param journalContentOutputData The output data containing results of a journal content operation.
     */
    void prepareSuccessView(JournalContentOutputData journalContentOutputData);
}

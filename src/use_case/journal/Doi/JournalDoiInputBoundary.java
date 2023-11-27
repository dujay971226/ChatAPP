package use_case.journal.Doi;

import java.io.IOException;

import java.io.IOException;

/**
 * The JournalDoiInputBoundary interface defines the contract for executing journal DOI-related operations.
 * Implementing classes should provide the logic to handle DOI-based journal searches and related actions.
 *
 * @author Xiaofeng Li
 */
public interface JournalDoiInputBoundary {

    /**
     * Executes a journal DOI-related operation based on the provided input data.
     *
     * @param journalDoiInputData The input data containing DOI information for the operation.
     * @throws IOException If an I/O error occurs during the execution of the operation.
     */
    public void execute(JournalDoiInputData journalDoiInputData) throws IOException;
}


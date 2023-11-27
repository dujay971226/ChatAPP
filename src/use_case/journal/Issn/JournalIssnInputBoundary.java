package use_case.journal.Issn;

import java.io.IOException;


/**
 * The JournalIssnInputBoundary interface defines the contract for executing journal operations based on International Standard Serial Numbers (ISSNs).
 * Implementing classes should provide the functionality to execute journal operations using ISSN input data.
 *
 * @author Xiaofeng Li
 */
public interface JournalIssnInputBoundary {

    /**
     * Executes a journal operation based on ISSN input data.
     *
     * @param journalIssnInputData The input data for the ISSN-based journal operation.
     * @throws IOException If an I/O error occurs during the execution of the operation.
     */
    void execute(JournalIssnInputData journalIssnInputData) throws IOException;
}


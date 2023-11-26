package use_case.journal.Content;

import java.io.IOException;

/**
 * Interface for the input boundary of journal content management.
 * This interface defines the methods required for handling the logic of journal content operations.
 *
 * @author Xiaofeng Li
 */
public interface JournalContentInputBoundary {

    /**
     * Executes the operation related to journal content management.
     * This method should contain the necessary logic to handle journal content operations.
     *
     * @param inputData The data needed for executing journal content operations.
     */
    void execute(JournalContentInputData inputData) throws IOException;
}


package interface_adapter.journal.searchbydoi;

import use_case.journal.Doi.JournalDoiInputBoundary;
import use_case.journal.Doi.JournalDoiInputData;

import java.io.IOException;

/**
 * Controller for managing DOI (Digital Object Identifier) operations in a journal.
 * This class is responsible for handling user interactions related to DOI operations
 * and delegating the processing to the JournalDoiInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class JournalDoiController {
    final JournalDoiInputBoundary journalDoiInputBoundary;

    /**
     * Constructs a JournalDoiController with a specified input boundary.
     * @param journalDoiInputBoundary The input boundary that provides the logic
     *                                for processing DOI operations.
     */
    public JournalDoiController(JournalDoiInputBoundary journalDoiInputBoundary) {
        this.journalDoiInputBoundary = journalDoiInputBoundary;
    }

    /**
     * Executes the operation related to DOI management in a journal.
     * This method encapsulates the logic to process the given DOI content.
     * @param content The DOI content to be processed.
     * @throws IOException If there is an issue in processing the content.
     */
    public void execute(String content) throws IOException {
        JournalDoiInputData journaDoiInputData = new JournalDoiInputData(content);
        journalDoiInputBoundary.execute(journaDoiInputData);
    }
}


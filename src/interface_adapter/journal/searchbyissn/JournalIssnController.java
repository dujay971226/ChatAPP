package interface_adapter.journal.searchbyissn;

import use_case.journal.Issn.JournalIssnInputBoundary;
import use_case.journal.Issn.JournalIssnInputData;

import java.io.IOException;

/**
 * Controller for managing ISSN (International Standard Serial Number) operations in a journal.
 * This class is responsible for handling user interactions related to ISSN operations
 * and delegating the processing to the JournalIssnInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class JournalIssnController {
    final JournalIssnInputBoundary JournalIssnInputBoundary;

    /**
     * Constructs a JournalIssnController with a specified input boundary.
     * @param JournalIssnInputBoundary The input boundary that provides the logic
     *                                 for processing ISSN operations.
     */
    public JournalIssnController(JournalIssnInputBoundary JournalIssnInputBoundary) {
        this.JournalIssnInputBoundary = JournalIssnInputBoundary;
    }

    /**
     * Executes the operation related to ISSN management in a journal.
     * This method encapsulates the logic to process the given ISSN content.
     * @param content The ISSN content to be processed.
     * @throws IOException If there is an issue in processing the content.
     */
    public void execute(String content) throws IOException {
        JournalIssnInputData journalIssnInputData = new JournalIssnInputData(content);
        JournalIssnInputBoundary.execute(journalIssnInputData);
    }
}


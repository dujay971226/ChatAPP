package interface_adapter.journal.searchbycontent;

import use_case.journal.Content.JournalContentInputBoundary;
import use_case.journal.Content.JournalContentInputData;

import java.io.IOException;

/**
 * Controller for managing journal content.
 * This class is responsible for handling user interactions related to journal content
 * and delegating the processing to the JournalContentInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class JournalContentController {
    final JournalContentInputBoundary journalContentInputBoundary;

    /**
     * Constructs a JournalContentController with a specified input boundary.
     * @param journalContentInputBoundary The input boundary that provides the logic
     *                                    for processing journal content.
     */
    public JournalContentController(JournalContentInputBoundary journalContentInputBoundary) {
        this.journalContentInputBoundary = journalContentInputBoundary;
    }

    /**
     * Executes the operation related to journal content management.
     * This method encapsulates the logic to process the given content.
     * @param content The content to be processed.
     * @throws IOException If there is an issue in processing the content.
     */
    public void execute(String content) throws IOException {
        JournalContentInputData journalContentInputData = new JournalContentInputData(content);
        journalContentInputBoundary.execute(journalContentInputData);
    }
}


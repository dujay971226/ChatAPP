package interface_adapter.journal.searchbyissn;

import use_case.journal.author.AuthorInputBoundary;
import use_case.journal.author.AuthorInputData;

import java.io.IOException;

/**
 * Controller for managing ISSN (International Standard Serial Number) operations in a journal.
 * This class is responsible for handling user interactions related to ISSN operations
 * and delegating the processing to the JournalIssnInputBoundary.
 *
 * @author Xiaofeng Li
 */
public class AuthorController {
    final AuthorInputBoundary AuthorInputBoundary;

    /**
     * Constructs a JournalIssnController with a specified input boundary.
     *
     * @param AuthorInputBoundary The input boundary that provides the logic
     *                            for processing ISSN operations.
     */
    public AuthorController(AuthorInputBoundary AuthorInputBoundary) {
        this.AuthorInputBoundary = AuthorInputBoundary;
    }

    /**
     * Executes the operation related to ISSN management in a journal.
     * This method encapsulates the logic to process the given ISSN content.
     *
     * @param content The ISSN content to be processed.
     * @throws IOException If there is an issue in processing the content.
     */
    public void execute(String content) throws IOException {
        AuthorInputData authorInputData = new AuthorInputData(content);
        AuthorInputBoundary.execute(authorInputData);
    }
}


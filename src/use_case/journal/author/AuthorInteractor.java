package use_case.journal.author;

import api.Journal;

import java.io.IOException;

/**
 * The JournalIssnInteractor class implements the business logic for executing journal operations based on International Standard Serial Numbers (ISSNs).
 * It uses the provided ISSN input data to search for journals by ISSN and prepares the output data for presentation.
 *
 * @author Xiaofeng Li
 */
public class AuthorInteractor implements AuthorInputBoundary {
    private final AuthorOutputBoundary authorOutputBoundary;

    /**
     * Constructs a JournalIssnInteractor object with the provided output boundary.
     *
     * @param authorOutputBoundary The output boundary for journal operations based on ISSN.
     */
    public AuthorInteractor(AuthorOutputBoundary authorOutputBoundary) {
        this.authorOutputBoundary = authorOutputBoundary;
    }

    /**
     * Executes journal operations based on ISSN input data.
     * It searches for journals by ISSN and prepares the output data for presentation.
     *
     * @param authorInputData The input data containing the author name.
     * @throws IOException If an IO error occurs during the execution.
     */
    @Override
    public void execute(AuthorInputData authorInputData) throws IOException {
        String result = Journal.searchAuthor(authorInputData.getIssn());
        AuthorOutputData authorOutputData = new AuthorOutputData(result);
        authorOutputBoundary.prepareSuccessView(authorOutputData);
    }
}


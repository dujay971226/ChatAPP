package use_case.journal.Issn;

import api.CORE;

import java.io.IOException;

/**
 * The JournalIssnInteractor class implements the business logic for executing journal operations based on International Standard Serial Numbers (ISSNs).
 * It uses the provided ISSN input data to search for journals by ISSN and prepares the output data for presentation.
 *
 * @author Xiaofeng Li
 */
public class JournalIssnInteractor implements JournalIssnInputBoundary {
    private final JournalIssnOutputBoundary journalIssnOutputBoundary;

    /**
     * Constructs a JournalIssnInteractor object with the provided output boundary.
     *
     * @param journalIssnOutputBoundary The output boundary for journal operations based on ISSN.
     */
    public JournalIssnInteractor(JournalIssnOutputBoundary journalIssnOutputBoundary) {
        this.journalIssnOutputBoundary = journalIssnOutputBoundary;
    }

    /**
     * Executes journal operations based on ISSN input data.
     * It searches for journals by ISSN and prepares the output data for presentation.
     *
     * @param journalIssnInputData The input data containing the ISSN content.
     * @throws IOException If an IO error occurs during the execution.
     */
    @Override
    public void execute(JournalIssnInputData journalIssnInputData) throws IOException {
        String result = CORE.searchJournalsByISSN(journalIssnInputData.getIssn());
        JournalIssnOutputData journalIssnOutputData = new JournalIssnOutputData(result);
        journalIssnOutputBoundary.prepareSuccessView(journalIssnOutputData);
    }
}


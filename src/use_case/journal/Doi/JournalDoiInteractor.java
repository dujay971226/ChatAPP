package use_case.journal.Doi;

import api.Journal;

import java.io.IOException;

/**
 * The JournalDoiInteractor class implements the JournalDoiInputBoundary interface and is responsible for
 * executing journal operations based on Digital Object Identifiers (DOIs).
 * It retrieves the DOI from the input data, performs a search using CORE, and prepares the output data.
 *
 * @author Xiaofeng Li
 */
public class JournalDoiInteractor implements JournalDoiInputBoundary {

    private final JournalDoiOutputBoundary journalDoiOutputBoundary;

    /**
     * Constructs a JournalDoiInteractor with the provided JournalDoiOutputBoundary.
     *
     * @param journalDoiOutputBoundary The output boundary for DOI-based journal operations.
     */
    public JournalDoiInteractor(JournalDoiOutputBoundary journalDoiOutputBoundary) {
        this.journalDoiOutputBoundary = journalDoiOutputBoundary;
    }

    /**
     * Executes the journal DOI-based operation using the provided input data.
     * It retrieves the DOI from the input data, performs a search, and prepares the output data.
     *
     * @param journalDoiInputData The input data containing the DOI for the operation.
     * @throws IOException If an IO error occurs during the operation.
     */
    @Override
    public void execute(JournalDoiInputData journalDoiInputData) throws IOException {
        // Retrieve the result by searching with the DOI
        String result = Journal.searchByDOI(journalDoiInputData.getDoi());

        // Prepare the output data
        JournalDoiOutputData journalDoiOutputData = new JournalDoiOutputData(result);

        // Notify the output boundary with the prepared output data
        journalDoiOutputBoundary.prepareSuccessView(journalDoiOutputData);
    }
}


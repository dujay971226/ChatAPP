package use_case.journal.Content;

import api.CORE;

import java.io.IOException;
/**
 * Interactor class for handling journal content operations.
 * Implements the JournalContentInputBoundary interface and contains the business logic
 * for processing journal content, such as searching or updating entries.
 *
 * @author Xiaofeng Li
 */
public class JournalContentInteractor implements JournalContentInputBoundary {

    private final JournalContentOutputBoundary journalContentOutputBoundary;

    /**
     * Constructs a JournalContentInteractor with the specified output boundary.
     *
     * @param journalContentOutputBoundary The output boundary for communicating results.
     */
    public JournalContentInteractor(JournalContentOutputBoundary journalContentOutputBoundary) {
        this.journalContentOutputBoundary = journalContentOutputBoundary;
    }

    /**
     * Executes the journal content operation based on the provided input data.
     * This method processes the input data, performs the necessary actions, and
     * communicates the results to the appropriate output boundary.
     *
     * @param journalContentInputData The input data containing parameters for the journal content operation.
     * @throws IOException If there is an I/O error during the operation.
     */
    @Override
    public void execute(JournalContentInputData journalContentInputData) throws IOException {
        String[] result = CORE.searchJournals(journalContentInputData.getContent());

        JournalContentOutputData journalContentOutputData = new JournalContentOutputData(result);
        journalContentOutputBoundary.prepareSuccessView(journalContentOutputData);
    }
}

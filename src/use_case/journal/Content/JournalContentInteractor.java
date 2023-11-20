package use_case.journal.Content;

import api.CORE;

import java.io.IOException;

public class JournalContentInteractor implements JournalContentInputBoundary {
    private final JournalContentOutputBoundary journalContentOutputBoundary;
    public JournalContentInteractor(JournalContentOutputBoundary journalContentOutputBoundary){this.journalContentOutputBoundary = journalContentOutputBoundary;}

    @Override
    public void execute(JournalContentInputData journalContentInputData) throws IOException {
        String[] result = CORE.searchJournals(journalContentInputData.getContent());
        JournalContentOutputData journalContentOutputData = new JournalContentOutputData(result);
        journalContentOutputBoundary.prepareSuccessView(journalContentOutputData);
    }
}

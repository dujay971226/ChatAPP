package use_case.Journal.Issn;

import api.CORE;

import java.io.IOException;

public class JournalIssnInteractor implements JournalIssnInputBoundary {
    private final JournalIssnOutputBoundary journalIssnOutputBoundary;
    public JournalIssnInteractor(JournalIssnOutputBoundary journalIssnOutputBoundary){this.journalIssnOutputBoundary = journalIssnOutputBoundary;}

    @Override
    public void execute(JournalIssnInputData journalIssnInputData) throws IOException {
        String result = CORE.searchJournalsByISSN(journalIssnInputData.getissn());
        JournalIssnOutputData journalIssnOutputData = new JournalIssnOutputData(result);
        journalIssnOutputBoundary.prepareSuccessView(journalIssnOutputData);
    }
}

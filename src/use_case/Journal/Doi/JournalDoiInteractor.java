package use_case.Journal.Doi;

import api.CORE;

import java.io.IOException;

public class JournalDoiInteractor implements JournalDoiInputBoundary {
    private final JournalDoiOutputBoundary journalDoiOutputBoundary;
    public JournalDoiInteractor(JournalDoiOutputBoundary journalDoiOutputBoundary){this.journalDoiOutputBoundary = journalDoiOutputBoundary;}

    @Override
    public void execute(JournalDoiInputData journalDoiInputData) throws IOException {
        String result = CORE.searchByDOI(journalDoiInputData.getdoi());
        JournalDoiOutputData journalDoiOutputData = new JournalDoiOutputData(result);
        journalDoiOutputBoundary.prepareSuccessView(journalDoiOutputData);
    }
}

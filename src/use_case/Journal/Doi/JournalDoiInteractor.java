package use_case.Journal.Doi;

import api.CORE;

import java.io.IOException;

public class JournalDoiInteractor implements JournalDoiInputBoundary {
    private final JournalDoiOutputBoundary journalContentOutputBoundary;
    public JournalDoiInteractor(JournalDoiOutputBoundary journalContentOutputBoundary){this.journalContentOutputBoundary = journalContentOutputBoundary;}

    @Override
    public void execute(JournalDoiInputData journalContentInputData) throws IOException {
        String result = CORE.searchByDOI(journalContentInputData.getdoi());
        JournalDoiOutputData journalDoiOutputData = new JournalDoiOutputData(result);
        journalContentOutputBoundary.prepareSuccessView(journalDoiOutputData);
    }
}

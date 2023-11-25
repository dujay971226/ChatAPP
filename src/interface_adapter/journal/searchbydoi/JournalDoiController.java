package interface_adapter.journal.searchbydoi;

import use_case.journal.Doi.JournalDoiInputBoundary;
import use_case.journal.Doi.JournalDoiInputData;

import java.io.IOException;

public class JournalDoiController {
    final JournalDoiInputBoundary journalDoiInputBoundary;
    public JournalDoiController(JournalDoiInputBoundary journalDoiInputBoundary){
        this.journalDoiInputBoundary = journalDoiInputBoundary;

    }
    public void execute(String content) throws IOException {
        JournalDoiInputData journaDoiInputData = new JournalDoiInputData(content);
        journalDoiInputBoundary.execute(journaDoiInputData);
    }

}

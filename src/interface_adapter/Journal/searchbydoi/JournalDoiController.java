package interface_adapter.Journal.searchbydoi;

import use_case.Journal.Content.JournalContentInputData;
import use_case.Journal.Doi.JournalDoiInputBoundary;
import use_case.Journal.Doi.JournalDoiInputData;

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

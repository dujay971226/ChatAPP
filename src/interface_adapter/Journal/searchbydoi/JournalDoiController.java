package interface_adapter.Journal.searchbydoi;

import use_case.Journal.Content.JournalContentInputBoundary;
import use_case.Journal.Content.JournalContentInputData;

import java.io.IOException;

public class JournalDoiController {
    final JournalContentInputBoundary journalContentInputBoundary;
    public JournalDoiController(JournalContentInputBoundary journalContentInputBoundary){
        this.journalContentInputBoundary = journalContentInputBoundary;

    }
    public void execute(String content) throws IOException {
        JournalContentInputData journalContentInputData = new JournalContentInputData(content);
        journalContentInputBoundary.execute(journalContentInputData);
    }

}

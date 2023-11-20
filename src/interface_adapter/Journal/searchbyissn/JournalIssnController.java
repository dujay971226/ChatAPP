package interface_adapter.Journal.searchbyissn;

import use_case.Journal.Content.JournalContentInputBoundary;
import use_case.Journal.Content.JournalContentInputData;

import java.io.IOException;

public class JournalIssnController {
    final JournalContentInputBoundary journalContentInputBoundary;
    public JournalIssnController(JournalContentInputBoundary journalContentInputBoundary){
        this.journalContentInputBoundary = journalContentInputBoundary;

    }
    public void execute(String content) throws IOException {
        JournalContentInputData journalContentInputData = new JournalContentInputData(content);
        journalContentInputBoundary.execute(journalContentInputData);
    }

}

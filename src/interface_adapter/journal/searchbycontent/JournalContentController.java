package interface_adapter.journal.searchbycontent;

import use_case.journal.Content.JournalContentInputBoundary;
import use_case.journal.Content.JournalContentInputData;

import java.io.IOException;

public class JournalContentController {
    final JournalContentInputBoundary journalContentInputBoundary;
    public JournalContentController(JournalContentInputBoundary journalContentInputBoundary){
        this.journalContentInputBoundary = journalContentInputBoundary;

    }
    public void execute(String content) throws IOException {
        JournalContentInputData journalContentInputData = new JournalContentInputData(content);
        journalContentInputBoundary.execute(journalContentInputData);
    }

}

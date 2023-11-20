package interface_adapter.Journal.searchbycontent;

import use_case.Journal.Content.JournalContentInputBoundary;
import use_case.Journal.Content.JournalContentInputData;

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

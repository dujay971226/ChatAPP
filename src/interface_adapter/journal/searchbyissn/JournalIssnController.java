package interface_adapter.journal.searchbyissn;

import use_case.journal.Issn.JournalIssnInputBoundary;
import use_case.journal.Issn.JournalIssnInputData;

import java.io.IOException;

public class JournalIssnController {
    final JournalIssnInputBoundary JournalIssnInputBoundary;
    public JournalIssnController(JournalIssnInputBoundary JournalIssnInputBoundary){
        this.JournalIssnInputBoundary = JournalIssnInputBoundary;

    }
    public void execute(String content) throws IOException {
        JournalIssnInputData journalIssnInputData = new JournalIssnInputData(content);
        JournalIssnInputBoundary.execute(journalIssnInputData);
    }

}

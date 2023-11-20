package use_case.journal.Issn;

import java.io.IOException;

public interface JournalIssnInputBoundary {
    public void execute(JournalIssnInputData journalIssnInputData) throws IOException;
}

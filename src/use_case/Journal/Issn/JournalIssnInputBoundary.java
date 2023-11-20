package use_case.Journal.Issn;

import java.io.IOException;

public interface JournalIssnInputBoundary {
    public void execute(JournalIssnInputData journalIssnInputData) throws IOException;
}

package use_case.journal.Doi;

import java.io.IOException;

public interface JournalDoiInputBoundary {
    public void execute(JournalDoiInputData journalDoiInputData) throws IOException;
}

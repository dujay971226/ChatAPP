package use_case.Journal.Doi;

import java.io.IOException;

public interface JournalDoiInputBoundary {
    public void execute(JournalDoiInputData journalDoiInputData) throws IOException;
}

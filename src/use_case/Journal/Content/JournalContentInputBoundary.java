package use_case.Journal.Content;

import java.io.IOException;

public interface JournalContentInputBoundary {
    public void execute(JournalContentInputData journalContentInputData) throws IOException;
}

package use_case.journal.Content;

/**
 * Data class for input data used in journal content operations.
 * This class encapsulates the data required for performing operations related to journal content,
 * such as searching or processing journal entries.
 *
 * @author Xiaofeng Li
 */
public class JournalContentInputData {

    private final String content;

    /**
     * Constructs a JournalContentInputData instance with the specified content.
     *
     * @param content The content related to a journal operation, such as search terms.
     */
    public JournalContentInputData(String content) {
        this.content = content;
    }

    /**
     * Retrieves the content associated with this journal operation.
     *
     * @return The content string.
     */
    public String getContent() {
        return content;
    }
}


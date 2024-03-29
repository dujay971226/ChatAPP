package use_case.journal.author;

/**
 * The JournalIssnInputData class represents input data for journal operations based on International Standard Serial Numbers (ISSNs).
 * It encapsulates the ISSN content provided for executing ISSN-based journal operations.
 *
 * @author Xiaofeng Li
 */
public class AuthorInputData {
    private final String issn;

    /**
     * Constructs a JournalIssnInputData object with the provided ISSN content.
     *
     * @param content The ISSN content.
     */
    public AuthorInputData(String content) {
        this.issn = content;
    }

    /**
     * Gets the ISSN content.
     *
     * @return The ISSN content.
     */
    public String getIssn() {
        return this.issn;
    }
}


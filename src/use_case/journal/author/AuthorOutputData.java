package use_case.journal.author;

/**
 * The JournalIssnOutputData class represents the output data of a journal operation based on International Standard Serial Number (ISSN).
 * It contains the result of the operation.
 *
 * @author Xiaofeng Li
 */
public class AuthorOutputData {
    private String result;

    /**
     * Constructs a JournalIssnOutputData object with the provided result.
     *
     * @param result The result of the ISSN-based journal operation.
     */
    public AuthorOutputData(String result){
        this.result = result;
    }

    /**
     * Get the result of the ISSN-based journal operation.
     *
     * @return The result string.
     */
    public String getResult() {
        return result;
    }
}


package use_case.journal.Content;

/**
 * This class represents the output data for journal content-related operations.
 * It encapsulates the result of a journal content operation, which can be a list
 * of strings.
 *
 * @author Xiaofeng Li
 */
public class JournalContentOutputData {

    /**
     * The result of the journal content operation represented as a string.
     */
    private String result = "";

    /**
     * Constructs a new instance of the JournalContentOutputData class.
     *
     * @param list An array of strings containing the results of the journal content operation.
     */
    public JournalContentOutputData(String[] list) {
        // Concatenate the array of strings into a single result string with line breaks.
        for (String s : list) {
            result = result + s + "\n";
        }
    }

    /**
     * Gets the result of the journal content operation as a formatted string.
     *
     * @return A string containing the results of the journal content operation.
     */
    public String getResult() {
        return result;
    }
}


package use_case.journal.author;

/**
 * The JournalIssnOutputBoundary interface defines the contract for presenting the results of journal operations based on International Standard Serial Numbers (ISSNs).
 * Implementing classes should provide a method to prepare a success view with output data.
 *
 * @author Xiaofeng Li
 */
public interface AuthorOutputBoundary {

    /**
     * Prepares a success view with the provided output data related to journal operations based on ISSN.
     *
     * @param authorOutputData The output data containing the result of the ISSN-based journal operation.
     */
    void prepareSuccessView(AuthorOutputData authorOutputData);
}


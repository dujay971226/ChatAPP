package use_case.journal.back;

/**
 * Interface for the output boundary of transitioning from a journal to a room.
 * This interface defines the methods required for outputting the results of
 * the transition from the journal view to the room view in the application.
 *
 * @author Xiaofeng Li
 */
public interface JournalToRoomOutputBoundary {

    /**
     * Method to be called when the transition from the journal view to the room view is successful.
     * This method should handle the necessary steps to communicate the successful transition to the presenter.
     */
    void prepareRoomView();
}


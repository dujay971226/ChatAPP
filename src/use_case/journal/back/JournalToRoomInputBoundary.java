package use_case.journal.back;

/**
 * Interface for the input boundary of transitioning from a journal to a room.
 * This interface defines the methods required for handling the logic of moving from
 * the journal view to the room view in the application.
 *
 * @author Xiaofeng Li
 */
public interface JournalToRoomInputBoundary {

    /**
     * Executes the operation to transition from the journal view to the room view.
     * Implementing this method should involve the necessary logic to handle the transition.
     */
    void execute();
}


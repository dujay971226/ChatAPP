package interface_adapter.journal.journaltoroom;

import use_case.journal.back.JournalToRoomInputBoundary;

/**
 * Controller class for managing the transition from the journal view to the room view.
 * This class handles the execution of the action to navigate from a journal to a room,
 * delegating the actual logic to the JournalToRoomInputBoundary.
 * @author Xiaofeng Li
 */
public class JournalToRoomController {
    final JournalToRoomInputBoundary journalToRoomInputBoundary;

    /**
     * Constructs a JournalToRoomController with a specified input boundary.
     * @param journalToRoomInputBoundary The input boundary that provides the logic
     *                                   for transitioning from a journal to a room.
     */
    public JournalToRoomController(JournalToRoomInputBoundary journalToRoomInputBoundary) {
        this.journalToRoomInputBoundary = journalToRoomInputBoundary;
    }

    /**
     * Executes the transition from the journal view to the room view.
     * The actual logic of the transition is encapsulated in the input boundary.
     */
    public void execute() {
        journalToRoomInputBoundary.execute();
    }
}


package use_case.journal.back;

/**
 * Interactor class for handling the transition from a journal view to a room view.
 * Implements the JournalToRoomInputBoundary interface and contains the business logic
 * for executing the transition between views.
 *
 * @author Xiaofeng Li
 */
public class JournalToRoomInteractor implements JournalToRoomInputBoundary {
    final JournalToRoomOutputBoundary journalToRoomOutputBoundary;

    public JournalToRoomInteractor(JournalToRoomOutputBoundary journalToRoomOutputBoundary) {
        this.journalToRoomOutputBoundary = journalToRoomOutputBoundary;
    }

    @Override
    public void execute() {
        journalToRoomOutputBoundary.prepareRoomView();
    }
}

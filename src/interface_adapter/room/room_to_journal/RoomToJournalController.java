package interface_adapter.room.room_to_journal;

import use_case.room.room_to_journal.RoomToJournalInputBoundary;

public class RoomToJournalController {

    final RoomToJournalInputBoundary roomToJournalUseCaseInteractor;
    public RoomToJournalController(RoomToJournalInputBoundary roomToJournalUseCaseInteractor) {
        this.roomToJournalUseCaseInteractor = roomToJournalUseCaseInteractor;
    }
    public void execute() {

        roomToJournalUseCaseInteractor.execute();
    }
}

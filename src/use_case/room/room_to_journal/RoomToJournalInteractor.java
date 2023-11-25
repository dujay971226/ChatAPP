package use_case.room.room_to_journal;

public class RoomToJournalInteractor implements RoomToJournalInputBoundary{

    final RoomToJournalOutputBoundary roomToJournalPresenter;

    public RoomToJournalInteractor(RoomToJournalOutputBoundary roomToJournalOutputBoundary) {
        this.roomToJournalPresenter = roomToJournalOutputBoundary;
    }

    @Override
    public void execute() {
        roomToJournalPresenter.prepareJournalView();
    }
}

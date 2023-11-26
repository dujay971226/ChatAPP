package use_case.journal.back;

public class JournalToRoomInteractor implements JournalToRoomInputBoundary{
    final JournalToRoomOutputBoundary journalToRoomOutputBoundary;
    public JournalToRoomInteractor(JournalToRoomOutputBoundary journalToRoomOutputBoundary){
        this.journalToRoomOutputBoundary = journalToRoomOutputBoundary;
    }

    @Override
    public void execute() {
        journalToRoomOutputBoundary.prepareRoomView();
    }
}

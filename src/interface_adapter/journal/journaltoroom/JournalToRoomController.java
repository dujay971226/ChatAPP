package interface_adapter.journal.journaltoroom;

import use_case.journal.back.JournalToRoomInputBoundary;

public class JournalToRoomController{
    final JournalToRoomInputBoundary journalToRoomInputBoundary;
    public JournalToRoomController(JournalToRoomInputBoundary journalToRoomInputBoundary){
        this.journalToRoomInputBoundary = journalToRoomInputBoundary;
    }
    public void execute() {
        journalToRoomInputBoundary.execute();
    }
}

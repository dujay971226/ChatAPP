package interface_adapter.room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomMessageInputData;

public class RoomToJournalController {

    final RoomInputBoundary roomToJournalUseCaseInteractor;
    public RoomToJournalController(RoomInputBoundary roomToJournalUseCaseInteractor) {
        this.roomToJournalUseCaseInteractor = roomToJournalUseCaseInteractor;
    }
    public void execute() {

        roomToJournalUseCaseInteractor.execute();
    }
}

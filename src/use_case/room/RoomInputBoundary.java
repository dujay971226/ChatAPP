package use_case.room;

import com.pubnub.api.PubNubException;

public interface RoomInputBoundary {
    void execute(RoomMessageInputData roomMessageInputData);

    void execute(RoomExitInputData roomExitInputData);
}

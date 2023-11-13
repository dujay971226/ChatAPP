package use_case.room;

import com.pubnub.api.PubNubException;

public interface RoomInputBoundary {
    void execute(RoomFileInputData roomFileInputData) throws PubNubException;

    void execute(RoomMessageInputData roomMessageInputData);
}

package use_case.room;

import com.pubnub.api.PubNubException;

public interface RoomInputBoundary {
    void execute(RoomMessageInputData roomMessageInputData);

    void execute(RoomReceiveInputData roomReceiveInputData);

    void execute(RoomExitInputData roomExitInputData);

    void execute(RoomToSettingInputData roomToSettingInputData);

    void execute();
}

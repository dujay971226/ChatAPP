package interface_adapter.room;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;
import use_case.room.RoomExitInputData;
import use_case.room.RoomInputBoundary;
import use_case.room.RoomToSettingInputData;

public class RoomToSettingController {
    final RoomInputBoundary roomToSettingUseCaseInteractor;
    public RoomToSettingController(RoomInputBoundary roomToSettingUseCaseInteractor) {
        this.roomToSettingUseCaseInteractor = roomToSettingUseCaseInteractor;
    }
    public void execute(User user, Channel channel, PubNub config) {
        RoomToSettingInputData roomToSettingInputData = new RoomToSettingInputData(user, channel, config);

        roomToSettingUseCaseInteractor.execute(roomToSettingInputData);

    }
}

package interface_adapter.room.room_to_setting;

import com.pubnub.api.PubNub;
import entity.Channel;
import entity.User;
import use_case.room.room_to_setting.RoomToSettingInputBoundary;
import use_case.room.room_to_setting.RoomToSettingInputData;

public class RoomToSettingController {
    final RoomToSettingInputBoundary roomToSettingUseCaseInteractor;
    public RoomToSettingController(RoomToSettingInputBoundary roomToSettingUseCaseInteractor) {
        this.roomToSettingUseCaseInteractor = roomToSettingUseCaseInteractor;
    }

    //Pass relative information to change to setting view
    public void execute(User user, Channel channel, PubNub config) {
        RoomToSettingInputData roomToSettingInputData = new RoomToSettingInputData(user, channel, config);

        roomToSettingUseCaseInteractor.execute(roomToSettingInputData);

    }
}

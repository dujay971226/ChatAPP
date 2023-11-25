package use_case.room.room_to_setting;

public class RoomToSettingInteractor implements RoomToSettingInputBoundary{

    final RoomToSettingOutputBoundary roomToSettingPresenter;

    public RoomToSettingInteractor(RoomToSettingOutputBoundary roomToSettingOutputBoundary) {
        this.roomToSettingPresenter = roomToSettingOutputBoundary;
    }
    @Override
    public void execute(RoomToSettingInputData roomToSettingInputData) {

        RoomToSettingOutputData roomToSettingOutputData = new RoomToSettingOutputData(roomToSettingInputData.getUser(),
                roomToSettingInputData.getChannel(), roomToSettingInputData.getConfig());

        roomToSettingPresenter.prepareSettingView(roomToSettingOutputData);

    }
}

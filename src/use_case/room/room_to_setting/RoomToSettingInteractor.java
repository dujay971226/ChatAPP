package use_case.room.room_to_setting;

public class RoomToSettingInteractor implements RoomToSettingInputBoundary {

    final RoomToSettingOutputBoundary roomToSettingPresenter;

    public RoomToSettingInteractor(RoomToSettingOutputBoundary roomToSettingOutputBoundary) {
        this.roomToSettingPresenter = roomToSettingOutputBoundary;
    }

    //Set relative information to setting state and change the view to setting view
    @Override
    public void execute(RoomToSettingInputData roomToSettingInputData) {

        RoomToSettingOutputData roomToSettingOutputData = new RoomToSettingOutputData(roomToSettingInputData.getUser(),
                roomToSettingInputData.getChannel(), roomToSettingInputData.getConfig());

        roomToSettingPresenter.prepareSettingView(roomToSettingOutputData);

    }
}

package use_case.room;
public interface RoomOutputBoundary {
    void prepareLostConnectionView();

    void prepareProfileView();

    void prepareNewMessageView(RoomOutputData roomOutputData);

    void prepareSettingView(RoomToSettingOutputData roomToSettingOutputData);

    void prepareJournalView();

    void prepareSentView();
}

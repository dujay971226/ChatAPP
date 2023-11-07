package use_case.room;
public interface ShowSettingOutputBoundary {
    void prepareSuccessView(ShowSettingOutputData showSettingOutputData);

    void prepareFailView(String error);
}

package use_case.channelsetting;
public interface ShowSettingOutputBoundary {
    void prepareSuccessView(ShowSettingOutputData showSettingOutputData);

    void prepareFailView(String error);
}

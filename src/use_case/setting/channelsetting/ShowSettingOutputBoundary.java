package use_case.setting.channelsetting;
public interface ShowSettingOutputBoundary {
    void prepareSuccessView(ShowSettingOutputData showSettingOutputData);

    void prepareFailView(String error);
}

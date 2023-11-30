package use_case.setting.settingtochannelhistory;

public interface SettingToChannelHistoryOutputBoundary {
    void prepareSuccessView(SettingToChannelHistoryOutputData returnToSettingOutputData);

    void prepareFailView(String error);

}

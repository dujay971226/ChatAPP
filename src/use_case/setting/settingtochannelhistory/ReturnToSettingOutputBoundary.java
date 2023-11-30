package use_case.setting.settingtochannelhistory;

public interface ReturnToSettingOutputBoundary {
    void prepareSuccessView(ReturnToSettingOutputData returnToSettingOutputData);
    void prepareFailView(String error);

}

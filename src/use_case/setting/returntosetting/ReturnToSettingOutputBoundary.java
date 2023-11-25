package use_case.setting.returntosetting;

public interface ReturnToSettingOutputBoundary {
    void prepareSuccessView(ReturnToSettingOutputData returnToSettingOutputData);
    void prepareFailView(String error);

}

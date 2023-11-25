package use_case.setting.returntosetting;

public class ReturnToSettingInteractor implements ReturnToSettingInputBoundary {
    final ReturnToSettingOutputBoundary returnToSettingPresenter;

    public ReturnToSettingInteractor(ReturnToSettingOutputBoundary returnToSettingOutputBoundary){
        this.returnToSettingPresenter = returnToSettingOutputBoundary;
    }
    @Override
    public void execute(ReturnToSettingInputData returnToSettingInputData) {
        returnToSettingPresenter.prepareSuccessView(new ReturnToSettingOutputData());
    }
}

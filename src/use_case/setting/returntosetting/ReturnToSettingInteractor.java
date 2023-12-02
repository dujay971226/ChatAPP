package use_case.setting.returntosetting;

public class ReturnToSettingInteractor implements ReturnToSettingInputBoundary {
    final ReturnToSettingOutputBoundary returnToSettingPresenter;

    public ReturnToSettingInteractor(ReturnToSettingOutputBoundary returnToSettingOutputBoundary) {
        this.returnToSettingPresenter = returnToSettingOutputBoundary;
    }

    // This use case meant to switch the view to the Setting View and pass necessary data to Setting State
    @Override
    public void execute(ReturnToSettingInputData returnToSettingInputData) {
        returnToSettingPresenter.prepareSuccessView(new ReturnToSettingOutputData(returnToSettingInputData.getChannelMessages()));
    }
}

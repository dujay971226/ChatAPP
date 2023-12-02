package use_case.setting.settingtochannelhistory;

public class SettingToChannelHistoryInteractor implements SettingToChannelHistoryInputBoundary {
    final SettingToChannelHistoryOutputBoundary returnToSettingPresenter;

    public SettingToChannelHistoryInteractor(SettingToChannelHistoryOutputBoundary returnToSettingOutputBoundary) {
        this.returnToSettingPresenter = returnToSettingOutputBoundary;
    }

    // This use case meant to switch the view to the Channel History View and pass necessary data to Channel History State
    @Override
    public void execute(SettingToChannelHistoryInputData returnToSettingInputData) {
        returnToSettingPresenter.prepareSuccessView(new SettingToChannelHistoryOutputData(returnToSettingInputData.getChannelName(), returnToSettingInputData.getConfig(), returnToSettingInputData.getUser()));
    }
}

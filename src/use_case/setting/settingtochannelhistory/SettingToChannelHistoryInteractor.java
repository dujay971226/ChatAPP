package use_case.setting.settingtochannelhistory;

public class SettingToChannelHistoryInteractor implements SettingToChannelHistoryInputBoundary {
    final SettingToChannelHistoryOutputBoundary returnToSettingPresenter;

    public SettingToChannelHistoryInteractor(SettingToChannelHistoryOutputBoundary returnToSettingOutputBoundary){
        this.returnToSettingPresenter = returnToSettingOutputBoundary;
    }
    @Override
    public void execute(SettingToChannelHistoryInputData returnToSettingInputData) {
        returnToSettingPresenter.prepareSuccessView(new SettingToChannelHistoryOutputData(returnToSettingInputData.getChannelName(), returnToSettingInputData.getConfig()));
    }
}

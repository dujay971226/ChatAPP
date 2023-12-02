package interface_adapter.setting.returntosetting;

import interface_adapter.ViewManagerModel;
import interface_adapter.setting.showsetting.SettingState;
import interface_adapter.setting.showsetting.SettingViewModel;
import use_case.setting.returntosetting.ReturnToSettingOutputBoundary;
import use_case.setting.returntosetting.ReturnToSettingOutputData;

public class ReturnToSettingPresenter implements ReturnToSettingOutputBoundary {
    private final SettingViewModel settingViewModel;
    private final ViewManagerModel viewManagerModel;

    public ReturnToSettingPresenter(ViewManagerModel viewManagerModel, SettingViewModel settingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingViewModel = settingViewModel;
    }

    @Override
    public void prepareSuccessView(ReturnToSettingOutputData response) {
        SettingState state = settingViewModel.getState();

        state.setChannelHistory(response.getChannelMessages());
        settingViewModel.setState(state);
        settingViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(settingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // This case will not happen at all, there's no reason except the process is aborted could stop the switch of view
    }
}

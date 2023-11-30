package interface_adapter.setting.settingtochannelhistory;

import interface_adapter.ViewManagerModel;
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
        this.viewManagerModel.setActiveView(settingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

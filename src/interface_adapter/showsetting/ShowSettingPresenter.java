package interface_adapter.showsetting;

import interface_adapter.ViewManagerModel;
import use_case.channelsetting.ShowSettingOutputBoundary;
import use_case.channelsetting.ShowSettingOutputData;

public class ShowSettingPresenter implements ShowSettingOutputBoundary {
    private final SettingViewModel settingViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowSettingPresenter(ViewManagerModel viewManagerModel,
                          SettingViewModel settingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingViewModel = settingViewModel;
    }

    @Override
    public void prepareSuccessView(ShowSettingOutputData response) {
        // On success, switch to the channel setting view.
        SettingState settingState = settingViewModel.getState();
        settingState.setChannelOccupancy(response.getChannelOccupancy());
        this.settingViewModel.setState(settingState);
        this.settingViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(settingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SettingState settingState = settingViewModel.getState();
        settingState.setLoadingSubscribersError(error);
        this.settingViewModel.setState(settingState);
        this.settingViewModel.firePropertyChanged();
    }
}

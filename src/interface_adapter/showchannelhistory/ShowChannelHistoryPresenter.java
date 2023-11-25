package interface_adapter.showchannelhistory;

import interface_adapter.ViewManagerModel;
import interface_adapter.showsetting.SettingState;
import interface_adapter.showsetting.SettingViewModel;
import use_case.showchannelhistory.ShowChannelHistoryOutputBoundary;
import use_case.showchannelhistory.ShowChannelHistoryOutputData;

public class ShowChannelHistoryPresenter implements ShowChannelHistoryOutputBoundary {
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final SettingViewModel settingViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowChannelHistoryPresenter(ChannelHistoryViewModel channelHistoryViewModel, SettingViewModel settingViewModel, ViewManagerModel viewManagerModel) {
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.settingViewModel = settingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        SettingState settingState = settingViewModel.getState();
        channelHistoryState.setChannelMessages(showChannelHistoryOutputData.getChannelMessages());
        channelHistoryViewModel.setState(channelHistoryState);
        channelHistoryViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(channelHistoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SettingState settingState = settingViewModel.getState();
        settingState.setChannelMessageError(error);
        settingViewModel.setState(settingState);
        settingViewModel.firePropertyChanged();
    }
}

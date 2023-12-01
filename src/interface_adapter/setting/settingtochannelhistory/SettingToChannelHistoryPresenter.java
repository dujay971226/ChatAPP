package interface_adapter.setting.settingtochannelhistory;

import interface_adapter.ViewManagerModel;
import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryOutputBoundary;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryOutputData;

public class SettingToChannelHistoryPresenter implements SettingToChannelHistoryOutputBoundary {
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ViewManagerModel viewManagerModel;

    public SettingToChannelHistoryPresenter(ViewManagerModel viewManagerModel, ChannelHistoryViewModel channelHistoryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.channelHistoryViewModel = channelHistoryViewModel;
    }

    @Override
    public void prepareSuccessView(SettingToChannelHistoryOutputData response) {
        ChannelHistoryState state = channelHistoryViewModel.getState();
        state.setConfig(response.getConfig());
        state.setChannel(response.getChannelName());
        state.setCurrentUser(response.getUser());
        state.setActive(true);
        channelHistoryViewModel.setState(state);
        channelHistoryViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(channelHistoryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

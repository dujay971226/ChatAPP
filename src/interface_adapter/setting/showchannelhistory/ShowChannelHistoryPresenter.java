package interface_adapter.setting.showchannelhistory;

import interface_adapter.ViewManagerModel;
import use_case.setting.showchannelhistory.ShowChannelHistoryOutputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryOutputData;

public class ShowChannelHistoryPresenter implements ShowChannelHistoryOutputBoundary {
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowChannelHistoryPresenter(ChannelHistoryViewModel channelHistoryViewModel, ViewManagerModel viewManagerModel) {
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        channelHistoryState.setChannelMessages(showChannelHistoryOutputData.getChannelMessages());
        channelHistoryState.setChannel(showChannelHistoryOutputData.getChannelName());
        channelHistoryState.setConfig(showChannelHistoryOutputData.getConfig());
        channelHistoryViewModel.setState(channelHistoryState);
        channelHistoryViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(channelHistoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        channelHistoryState.setChannelMessageError(error);
        channelHistoryViewModel.setState(channelHistoryState);
        channelHistoryViewModel.firePropertyChanged();
    }
}

package interface_adapter.setting.deletemessage;

import interface_adapter.ViewManagerModel;
import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import use_case.setting.deletemessage.DeleteMessageOutputBoundary;
import use_case.setting.deletemessage.DeleteMessageOutputData;

public class DeleteMessagePresenter implements DeleteMessageOutputBoundary {
    private final ChannelHistoryViewModel channelHistoryViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteMessagePresenter(ChannelHistoryViewModel channelHistoryViewModel, ViewManagerModel viewManagerModel) {
        this.channelHistoryViewModel = channelHistoryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteMessageOutputData deleteMessageOutputData) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        channelHistoryState.setChannelMessages(deleteMessageOutputData.getChannelMessages());
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

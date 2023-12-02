package interface_adapter.setting.deletemessage;

import interface_adapter.setting.showchannelhistory.ChannelHistoryState;
import interface_adapter.setting.showchannelhistory.ChannelHistoryViewModel;
import use_case.setting.deletemessage.DeleteMessageOutputBoundary;
import use_case.setting.deletemessage.DeleteMessageOutputData;

import java.util.HashMap;

public class DeleteMessagePresenter implements DeleteMessageOutputBoundary {
    private final ChannelHistoryViewModel channelHistoryViewModel;

    public DeleteMessagePresenter(ChannelHistoryViewModel channelHistoryViewModel) {
        this.channelHistoryViewModel = channelHistoryViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteMessageOutputData deleteMessageOutputData) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        channelHistoryState.setDeleteMessages(new HashMap<>());
        channelHistoryState.setUpdateDelete(true);
        channelHistoryViewModel.setState(channelHistoryState);
        channelHistoryViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ChannelHistoryState channelHistoryState = channelHistoryViewModel.getState();
        channelHistoryState.setDeleteMessages(new HashMap<>());
        channelHistoryState.setDeleteMessageError(error);
        channelHistoryViewModel.setState(channelHistoryState);
        channelHistoryViewModel.firePropertyChanged();
    }
}

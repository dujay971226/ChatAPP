package interface_adapter.showchannelhistory;

import use_case.showchannelhistory.ShowChannelHistoryInputBoundary;
import use_case.showchannelhistory.ShowChannelHistoryInputData;

import java.time.LocalDateTime;

public class ShowChannelHistoryController {
    private final ShowChannelHistoryInputBoundary showChannelHistoryInteractor;

    public ShowChannelHistoryController(ShowChannelHistoryInputBoundary showChannelHistoryInteractor) {
        this.showChannelHistoryInteractor = showChannelHistoryInteractor;
    }


    public void execute(String channelName) {
        ShowChannelHistoryInputData showChannelHistoryInputData = new ShowChannelHistoryInputData(LocalDateTime.now(), channelName);

        showChannelHistoryInteractor.execute(showChannelHistoryInputData);
    }
}

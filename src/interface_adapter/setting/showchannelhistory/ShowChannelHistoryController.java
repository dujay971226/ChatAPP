package interface_adapter.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import use_case.setting.showchannelhistory.ShowChannelHistoryInputBoundary;
import use_case.setting.showchannelhistory.ShowChannelHistoryInputData;

import java.time.LocalDateTime;

public class ShowChannelHistoryController {
    private final ShowChannelHistoryInputBoundary showChannelHistoryInteractor;

    public ShowChannelHistoryController(ShowChannelHistoryInputBoundary showChannelHistoryInteractor) {
        this.showChannelHistoryInteractor = showChannelHistoryInteractor;
    }


    public void execute(String channelName, PubNub config) {
        ShowChannelHistoryInputData showChannelHistoryInputData = new ShowChannelHistoryInputData(LocalDateTime.now(), channelName, config);

        showChannelHistoryInteractor.execute(showChannelHistoryInputData);
    }
}

package use_case.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShowChannelHistoryInteractor implements ShowChannelHistoryInputBoundary{

    final ShowChannelHistoryOutputBoundary showChannelHistoryPresenter;

    public ShowChannelHistoryInteractor( ShowChannelHistoryOutputBoundary showChannelHistoryOutputBoundary){
        this.showChannelHistoryPresenter = showChannelHistoryOutputBoundary;
    }
    @Override
    public void execute(ShowChannelHistoryInputData showChannelHistoryInputData) {

        LocalDateTime endDateTime = showChannelHistoryInputData.getEndTime();
        ZonedDateTime zdt = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
        long endTime = zdt.toInstant().toEpochMilli();

        String channelName = showChannelHistoryInputData.getChannelName();
        ArrayList<String> channels = new ArrayList<String>();

        channels.add(channelName);
    }
}

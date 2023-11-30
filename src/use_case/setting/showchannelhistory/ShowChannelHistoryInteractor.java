package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShowChannelHistoryInteractor implements ShowChannelHistoryInputBoundary{
    final ShowChannelHistoryOutputBoundary showChannelHistoryPresenter;

    public ShowChannelHistoryInteractor(ShowChannelHistoryOutputBoundary showChannelHistoryOutputBoundary){
        this.showChannelHistoryPresenter = showChannelHistoryOutputBoundary;
    }
    @Override
    public void execute(ShowChannelHistoryInputData showChannelHistoryInputData) {
        PubNub pubnub = showChannelHistoryInputData.getConfig();

        LocalDateTime endDateTime = showChannelHistoryInputData.getEndTime();
        ZonedDateTime zdt = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
        long endTime = zdt.toInstant().toEpochMilli();

        String channelName = showChannelHistoryInputData.getChannelName();

        pubnub.fetchMessages()
                .channels(Arrays.asList(channelName))
                .maximumPerChannel(100)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(PNFetchMessagesResult result, PNStatus status) {
                        if (!status.isError()) {
                            Map<String, List<PNFetchMessageItem>> channels = result.getChannels();
                            ShowChannelHistoryOutputData showSettingOutputData = new ShowChannelHistoryOutputData(channels.get(channelName), pubnub, channelName);
                            showChannelHistoryPresenter.prepareSuccessView(showSettingOutputData);
                        } else {
                            showChannelHistoryPresenter.prepareFailView(status.getErrorData().toString());
                        }
                    }
                });
    }
}

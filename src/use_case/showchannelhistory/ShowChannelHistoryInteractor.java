package use_case.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import data_access.TokenDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShowChannelHistoryInteractor implements ShowChannelHistoryInputBoundary{
    final TokenDataAccessInterface pubNubDataAccessObject;
    final ShowChannelHistoryOutputBoundary showChannelHistoryPresenter;

    public ShowChannelHistoryInteractor(TokenDataAccessInterface tokenDataAccessInterface, ShowChannelHistoryOutputBoundary showChannelHistoryOutputBoundary){
        this.pubNubDataAccessObject = tokenDataAccessInterface;
        this.showChannelHistoryPresenter = showChannelHistoryOutputBoundary;
    }
    @Override
    public void execute(ShowChannelHistoryInputData showChannelHistoryInputData) {
        PubNub pubnub = this.pubNubDataAccessObject.getPubNubAccess();

        LocalDateTime endDateTime = showChannelHistoryInputData.getEndTime();
        ZonedDateTime zdt = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
        long endTime = zdt.toInstant().toEpochMilli();

        String channelName = showChannelHistoryInputData.getChannelName();
        ArrayList<String> channels = new ArrayList<String>();

        channels.add(channelName);
        pubnub.fetchMessages()
                .channels(channels)
                .maximumPerChannel(25)
                .end(endTime);

        pubnub.fetchMessages()
                .channels(Arrays.asList(channelName))
                .maximumPerChannel(25)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(PNFetchMessagesResult result, PNStatus status) {
                        if (!status.isError()) {
                            Map<String, List<PNFetchMessageItem>> channels = result.getChannels();
                            ShowChannelHistoryOutputData showSettingOutputData = new ShowChannelHistoryOutputData(channels.get(channelName));
                            showChannelHistoryPresenter.prepareSuccessView(showSettingOutputData);
                        } else {
                            showChannelHistoryPresenter.prepareFailView(status.getErrorData().toString());
                        }
                    }
                });
    }
}

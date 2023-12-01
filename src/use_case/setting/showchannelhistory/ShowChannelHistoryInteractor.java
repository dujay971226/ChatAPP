package use_case.setting.showchannelhistory;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import entity.Message;
import entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ShowChannelHistoryInteractor implements ShowChannelHistoryInputBoundary {
    final ShowChannelHistoryOutputBoundary showChannelHistoryPresenter;

    public ShowChannelHistoryInteractor(ShowChannelHistoryOutputBoundary showChannelHistoryOutputBoundary) {
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
                .channels(Collections.singletonList(channelName))
                .maximumPerChannel(100)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(PNFetchMessagesResult result, PNStatus status) {
                        if (!status.isError()) {
                            List<PNFetchMessageItem> channelMessages = result.getChannels().get(channelName);
                            ArrayList<Message> messages = new ArrayList<>();
                            for (PNFetchMessageItem messageItem: channelMessages){
                                String username = messageItem.getUuid();
                                String usermessage = messageItem.getMessage().getAsJsonObject().get("msg").toString();
                                String content = usermessage.substring(username.length() + 3, usermessage.length() - 1);
                                messages.add(new Message(new User(username), content, messageItem.getTimetoken()));
                            }
                            ShowChannelHistoryOutputData showSettingOutputData = new ShowChannelHistoryOutputData(messages, pubnub, channelName);
                            showChannelHistoryPresenter.prepareSuccessView(showSettingOutputData);
                        } else {
                            showChannelHistoryPresenter.prepareFailView(status.getErrorData().toString());
                        }
                    }
                });
    }
}

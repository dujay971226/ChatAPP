package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import entity.Channel;
import entity.Message;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

/**
 * Interactor of subscribe room.
 */
public class SubscribeRoomInteractor implements SubscribeRoomInputBoundary {

    final SubscribeRoomOutputBoundary subscribeRoomPresenter;

    /**
     * Initializes a SubscribeRoomInteractor instance.
     * @param subscribeRoomOutputBoundary presenter of subscribe room
     */
    public SubscribeRoomInteractor(SubscribeRoomOutputBoundary subscribeRoomOutputBoundary) {
        this.subscribeRoomPresenter = subscribeRoomOutputBoundary;
    }

    /**
     * Executes by calling data access object.
     * @param subscribeRoomInputData input data
     */
    @Override
    public void execute(SubscribeRoomInputData subscribeRoomInputData) {
        if (!exists(subscribeRoomInputData.getChannelName(), subscribeRoomInputData.getChannelLog())) {
            ArrayList<Message> messageLog = getMessageLog(subscribeRoomInputData.getChannelName(),
                    subscribeRoomInputData.getConfig(), subscribeRoomInputData.getUser());
            SubscribeRoomOutputData outputData = new SubscribeRoomOutputData(subscribeRoomInputData.getChannelName(),
                    subscribeRoomInputData.getConfig(), subscribeRoomInputData.getUser(), messageLog);
            subscribeRoomPresenter.prepareSuccessView(outputData);
        } else {
            subscribeRoomPresenter.prepareFailView("Channel name does not exist, try again.");
        }
    }

    // Checks if channel exists in an arraylist of channel.
    private boolean exists(String channel, ArrayList<Channel> channels) {
        for (Channel c : channels) {
            if (channel.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    // Returns message history using pubnub.
    private ArrayList<Message> getMessageLog(String channelName, PubNub pubNub, User user) {
        ArrayList<Message> messageLog = new ArrayList<>();
        pubNub.fetchMessages()
                .channels(Arrays.asList(channelName))
                .maximumPerChannel(25)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async(new PNCallback<PNFetchMessagesResult>() {
                    @Override
                    public void onResponse(@Nullable PNFetchMessagesResult pnFetchMessagesResult, @NotNull PNStatus pnStatus) {
                        if (!pnStatus.isError()) {
                            Map<String, List<PNFetchMessageItem>> channels = pnFetchMessagesResult.getChannels();
                            for (PNFetchMessageItem messageItem : channels.get(channelName)) {
                                long time = messageItem.getTimetoken() / 10000000L;
                                LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                        TimeZone.getDefault().toZoneId());
                                Message mes = new Message(user, messageItem.getMessage().getAsString(),
                                        localDateTime);
                                messageLog.add(mes);
                            }
                        }
                    }
                });
        return messageLog;
    }

}

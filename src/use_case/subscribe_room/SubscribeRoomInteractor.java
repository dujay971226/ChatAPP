package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import data_access.iChannelDataAccessObject;
import entity.Channel;
import entity.Message;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Interactor of subscribe room.
 * @author huangzhihao
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
        PubNub pubNub = subscribeRoomInputData.getConfig();
        String channelName = subscribeRoomInputData.getChannelName();
        User user = subscribeRoomInputData.getUser();
        sendOutputData(channelName, pubNub, user);
    }

    // Returns message history using pubnub.
    private void sendOutputData(String channelName, PubNub pubNub, User user) {
        pubNub.fetchMessages()
                .channels(Collections.singletonList(channelName))
                .maximumPerChannel(10)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async((pnFetchMessagesResult, pnStatus) -> {
                    if (!pnStatus.isError()) {
                        ArrayList<Message> messageLog = new ArrayList<>();
                        Map<String, List<PNFetchMessageItem>> channels = pnFetchMessagesResult.getChannels();
                        if (channels.get(channelName) != null) { // if channel exists, get message log
                            for (PNFetchMessageItem messageItem : channels.get(channelName)) {
                                long time = messageItem.getTimetoken() / 10000000L;
                                LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                        TimeZone.getDefault().toZoneId());
                                String mesString = messageItem.getMessage().getAsJsonObject().
                                        getAsJsonPrimitive("msg").toString();
                                mesString = mesString.substring(1, mesString.length() - 1); // remove quotation marks
                                Message mes = new Message(user, mesString,
                                        localDateTime);
                                messageLog.add(mes);
                            }
                        }
                        pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();
                        SubscribeRoomOutputData outputData = new SubscribeRoomOutputData(channelName,
                                pubNub, user, messageLog);
                        subscribeRoomPresenter.prepareSuccessView(outputData);
                    }
                });
    }

}

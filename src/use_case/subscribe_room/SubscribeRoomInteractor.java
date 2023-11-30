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
        ArrayList<Message> messageLog = getMessageLog(subscribeRoomInputData.getChannelName(),
                subscribeRoomInputData.getConfig(), subscribeRoomInputData.getUser());
        SubscribeRoomOutputData outputData = new SubscribeRoomOutputData(subscribeRoomInputData.getChannelName(),
                subscribeRoomInputData.getConfig(), subscribeRoomInputData.getUser(), messageLog);
        pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();

        for (Message mes : messageLog) {
            System.out.println(mes.getContent());
        }
        subscribeRoomPresenter.prepareSuccessView(outputData);

    }

    // Returns message history using pubnub.
    private ArrayList<Message> getMessageLog(String channelName, PubNub pubNub, User user) {
        ArrayList<Message> messageLog = new ArrayList<>();
        pubNub.fetchMessages()
                .channels(Collections.singletonList(channelName))
                .maximumPerChannel(10)
                .includeMessageActions(true)
                .includeMeta(true)
                .includeMessageType(true)
                .includeUUID(true)
                .async((pnFetchMessagesResult, pnStatus) -> {
                    if (!pnStatus.isError()) {
                        Map<String, List<PNFetchMessageItem>> channels = pnFetchMessagesResult.getChannels();
                        if (channels.get(channelName) != null) {
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
                        }  // return empty arraylist if channels is null
                    }
                });
        try {
            Thread.sleep(1000);
        } catch (IllegalArgumentException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return messageLog;
    }

}

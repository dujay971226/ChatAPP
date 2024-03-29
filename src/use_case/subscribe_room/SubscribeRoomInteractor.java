package use_case.subscribe_room;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import data_access.iChannelDataAccessObject;
import entity.Message;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Interactor of subscribe room.
 *
 * @author huangzhihao
 */
public class SubscribeRoomInteractor implements SubscribeRoomInputBoundary {

    final SubscribeRoomOutputBoundary subscribeRoomPresenter;
    final iChannelDataAccessObject channelDataAccessObject;

    /**
     * Initializes a SubscribeRoomInteractor instance.
     *
     * @param subscribeRoomOutputBoundary presenter of subscribe room
     */
    public SubscribeRoomInteractor(SubscribeRoomOutputBoundary subscribeRoomOutputBoundary,
                                   iChannelDataAccessObject channelDataAccessObject) {
        this.subscribeRoomPresenter = subscribeRoomOutputBoundary;
        this.channelDataAccessObject = channelDataAccessObject;
    }

    /**
     * Executes by calling data access object.
     *
     * @param subscribeRoomInputData input data
     */
    @Override
    public void execute(SubscribeRoomInputData subscribeRoomInputData) {
        PubNub pubNub = subscribeRoomInputData.getConfig();
        String channelName = subscribeRoomInputData.getChannelName();
        channelDataAccessObject.save(subscribeRoomInputData.getChannelName(), subscribeRoomInputData.getUser());
        pubNub.subscribe().channels(Collections.singletonList(channelName)).execute();
        ArrayList<Message> messageLog = new ArrayList<>();

        pubNub.fetchMessages()
                .channels(Collections.singletonList(channelName))
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
                            SubscribeRoomOutputData outputData = new SubscribeRoomOutputData(subscribeRoomInputData.getChannelName(),
                                    subscribeRoomInputData.getConfig(), subscribeRoomInputData.getUser(), messageLog);
                            if (channels.get(channelName) != null) {
                                for (PNFetchMessageItem messageItem : channels.get(channelName)) {
                                    long time = messageItem.getTimetoken() / 10000000L;
                                    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                            TimeZone.getDefault().toZoneId());
                                    String mesString = messageItem.getMessage().getAsJsonObject().
                                            getAsJsonPrimitive("msg").toString();
                                    mesString = mesString.substring(1, mesString.length() - 1); // remove quotation marks
                                    Message mes = new Message(new User(messageItem.getUuid()), mesString,
                                            localDateTime);
                                    messageLog.add(mes);
                                }
                            } // return empty arraylist if channels is null
                            subscribeRoomPresenter.prepareSuccessView(outputData);
                        }
                    }
                });
    }
}

package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNDeleteMessagesResult;
import com.pubnub.api.models.consumer.history.PNFetchMessageItem;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DeleteMessageInteractor implements DeleteMessageInputBoundary {
    final DeleteMessageOutputBoundary deleteMessagePresenter;

    public DeleteMessageInteractor(DeleteMessageOutputBoundary deleteMessageOutputBoundary){
        this.deleteMessagePresenter = deleteMessageOutputBoundary;
    }
    @Override
    public void execute(DeleteMessageInputData deleteMessageInputData) {

        // getting all the needed data from the InputData
        PubNub pubnub = deleteMessageInputData.getConfig();

        long startTime = deleteMessageInputData.getStartTime();
        long endTime = deleteMessageInputData.getEndTime();

        String channelName = deleteMessageInputData.getChannelName();

        pubnub.deleteMessages()
                .channels(Arrays.asList(channelName))
                .start(startTime)
                .end(endTime)
                .async(new PNCallback<PNDeleteMessagesResult>() {
                    @Override
                    public void onResponse(PNDeleteMessagesResult result, PNStatus status) {
                        // The deleteMessages() method does not return actionable data, be sure to check the status
                        // object on the outcome of the operation by checking the status.isError().
                    }
                });
    }
}

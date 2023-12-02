package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNDeleteMessagesResult;
import entity.Message;

import java.util.Collections;
import java.util.HashMap;

public class DeleteMessageInteractor implements DeleteMessageInputBoundary {
    final DeleteMessageOutputBoundary deleteMessagePresenter;

    public DeleteMessageInteractor(DeleteMessageOutputBoundary deleteMessageOutputBoundary) {
        this.deleteMessagePresenter = deleteMessageOutputBoundary;
    }

    @Override
    public void execute(DeleteMessageInputData deleteMessageInputData) {

        // getting all the needed data from the InputData
        PubNub pubnub = deleteMessageInputData.getConfig();

        String channelName = deleteMessageInputData.getChannelName();

        Object[] startTimes = deleteMessageInputData.getStartTimeLists();
        HashMap<Long, Message> deleteMessages = deleteMessageInputData.getDeleteMessages();

        // for each timestamp in the startTimeLists, delete their corresponding messages.
        for (int i = 0; i < startTimes.length; i++) {
            long startT = (Long) startTimes[i];
            long endT = deleteMessages.get(startT).getEndTimeStamp();
            pubnub.deleteMessages()
                    .channels(Collections.singletonList(channelName))
                    .start(startT)
                    .end(endT)
                    .async(new PNCallback<PNDeleteMessagesResult>() {
                        @Override
                        public void onResponse(PNDeleteMessagesResult result, PNStatus status) {
                            // The deleteMessages() method does not return actionable data, be sure to check the status
                            // object on the outcome of the operation by checking the status.isError().
                            if (status.isError()) {
                                deleteMessagePresenter.prepareFailView("Error: " + status.getErrorData().getInformation() + "happens when deleting message with timestamp:" + startT);
                            }
                        }
                    });
        }
        deleteMessagePresenter.prepareSuccessView(new DeleteMessageOutputData());

    }
}

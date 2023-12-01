package use_case.setting.deletemessage;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNDeleteMessagesResult;

import java.util.Arrays;
import java.util.Collections;

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

        Object[] startTimeLists = deleteMessageInputData.getStartTimeLists();

        // for each timestamp in the startTimeLists, delete their corresponding messages.
        for (Object start : startTimeLists) {
            long startTime = (Long) start;
            long startT = startTime - 1;
            long endT = startTime + 1;
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
                                deleteMessagePresenter.prepareFailView("Error: " + status.getErrorData().getInformation() + "happens when deleting message with timestamp:" + startTime);
                            }
                        }
                    });
        }
        deleteMessagePresenter.prepareSuccessView(new DeleteMessageOutputData());

    }
}
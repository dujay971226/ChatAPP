package interface_adapter.setting.deletemessage;

import com.pubnub.api.PubNub;
import use_case.setting.deletemessage.DeleteMessageInputBoundary;
import use_case.setting.deletemessage.DeleteMessageInputData;

import java.time.LocalDateTime;

public class DeleteMessageController {
    private final DeleteMessageInputBoundary deleteMessageInteractor;

    public DeleteMessageController(DeleteMessageInputBoundary deleteMessageInteractor) {
        this.deleteMessageInteractor = deleteMessageInteractor;
    }


    public void execute(long startTime, long endTime, String channelName, PubNub config) {
        DeleteMessageInputData deleteMessageInputData = new DeleteMessageInputData(startTime, endTime, channelName, config);

        deleteMessageInteractor.execute(deleteMessageInputData);
    }
    public void execute(long[] startTime, String channelName, PubNub config) {
        DeleteMessageInputData deleteMessageInputData = new DeleteMessageInputData(startTime, channelName, config);

        deleteMessageInteractor.execute(deleteMessageInputData);
    }
}

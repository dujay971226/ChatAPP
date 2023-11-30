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

    public void execute(Object[] startTime, String channelName, PubNub config) {
        DeleteMessageInputData deleteMessageInputData = new DeleteMessageInputData(startTime, channelName, config);

        deleteMessageInteractor.execute(deleteMessageInputData);
    }
}
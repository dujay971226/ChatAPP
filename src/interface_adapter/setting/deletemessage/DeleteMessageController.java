package interface_adapter.setting.deletemessage;

import com.pubnub.api.PubNub;
import entity.Message;
import use_case.setting.deletemessage.DeleteMessageInputBoundary;
import use_case.setting.deletemessage.DeleteMessageInputData;

import java.util.HashMap;

public class DeleteMessageController {
    private final DeleteMessageInputBoundary deleteMessageInteractor;

    public DeleteMessageController(DeleteMessageInputBoundary deleteMessageInteractor) {
        this.deleteMessageInteractor = deleteMessageInteractor;
    }

    public void execute(Object[] startTime, HashMap<Long, Message> deleteMessage, String channelName, PubNub config) {
        DeleteMessageInputData deleteMessageInputData = new DeleteMessageInputData(startTime, deleteMessage, channelName, config);

        deleteMessageInteractor.execute(deleteMessageInputData);
    }
}

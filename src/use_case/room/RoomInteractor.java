package use_case.room;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;

public class RoomInteractor implements RoomInputBoundary{
    @Override
    public void execute(RoomFileInputData roomFileInputData) throws PubNubException {

    }

    @Override
    public void execute(RoomMessageInputData roomMessageInputData) {
        UserId userId = RoomMessageInputData.getUser().getID();
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");

        PubNub pubnub = new PubNub(pnConfiguration);

        final JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", roomMessageInputData.getContent());

        pubnub.publish()
                .channel(roomMessageInputData.getChannel().getChannelName())
                .message(messageJsonObject)
                .async((result, publishStatus) -> {
                    if (!publishStatus.isError()) {
                    }
                    // Request processing failed.
                    else {
                        System.out.println("Send Message Failed");
                    }
                });
    }
}

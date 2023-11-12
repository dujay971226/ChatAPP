package use_case.subscribe_room;

import java.awt.*;

public interface SubscribeRoomInputBoundary {
    void execute(SubscribeRoomInputData subscribeRoomInputData);

    String[] getChannels();
}

package use_case.subscribe_room;

import java.awt.*;

/**
 * Input boundary of subscribe room.
 */
public interface SubscribeRoomInputBoundary {

    /**
     * Executes using input data.
     * @param subscribeRoomInputData input data.
     */
    void execute(SubscribeRoomInputData subscribeRoomInputData);

    /**
     * Gets the channels as a string array.
     * @return channel stirng array
     */
    String[] getChannels();
}

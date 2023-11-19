package use_case.subscribe_room;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    ArrayList<String> getChannels();
}

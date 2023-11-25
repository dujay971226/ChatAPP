package use_case.subscribe_room;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Input boundary of subscribe room.
 * @author huangzhihao
 */
public interface SubscribeRoomInputBoundary {

    /**
     * Executes using input data.
     * @param subscribeRoomInputData input data.
     */
    void execute(SubscribeRoomInputData subscribeRoomInputData);

}

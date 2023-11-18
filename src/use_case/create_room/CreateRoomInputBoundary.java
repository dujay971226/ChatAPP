package use_case.create_room;

import java.awt.*;

/**
 * The input boundary of create room.
 */
public interface CreateRoomInputBoundary {

    /**
     * Executes given an input data.
     * @param createRoomInputData input data of create room
     */
    void execute(CreateRoomInputData createRoomInputData);

}

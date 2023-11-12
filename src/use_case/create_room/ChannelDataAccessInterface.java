package use_case.create_room;

import com.pubnub.api.UserId;

public interface ChannelDataAccessInterface {

    String generateToken(UserId userid, Integer ttl);

}

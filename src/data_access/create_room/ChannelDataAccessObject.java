package data_access.create_room;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.access_manager.v3.PNGrantTokenResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import use_case.create_room.ChannelDataAccessInterface;

public class ChannelDataAccessObject implements ChannelDataAccessInterface {

    public ChannelDataAccessObject() {}

    @Override
    public String generateToken(UserId userid, Integer ttl) {
        try {
            PubNub pubNub = new PubNub(new PNConfiguration(userid));
            return pubNub.grantToken(ttl).toString();
        } catch (PubNubException e) {
            throw new RuntimeException(e);
        }
    }
}

package data_access;

import com.pubnub.api.PubNub;
import entity.PubNubAccess;

public class PubNubDataAccessObject implements TokenDataAccessInterface{
    PubNubAccess pubNubAccess;
    public PubNubDataAccessObject(PubNubAccess pubNubAccess){
        this.pubNubAccess = pubNubAccess;
    }
    @Override
    public PubNub getPubNubAccess() {
        return pubNubAccess.getPubNub();
    }
}

package entity;

import com.pubnub.api.PubNub;

public class PubNubAccess {
    PubNub pubnub;
    public PubNubAccess(PubNub pubnub){
        this.pubnub = pubnub;
    }

    public PubNub getPubNub() {
        return pubnub;
    }
}

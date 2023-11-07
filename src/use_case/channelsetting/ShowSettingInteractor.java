package use_case.channelsetting;

import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;
import data_access.ChannelUsersDataAccessInterface;
import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.PNStatus;

import java.util.Arrays;

public class ShowSettingInteractor implements ShowSettingInputBoundary {
    final ChannelUsersDataAccessInterface channelUsersDataAccessObject;
    final ShowSettingOutputBoundary showSettingPresenter;
    final PubNub pubnub;

    public ShowSettingInteractor(PubNub pubnub, ChannelUsersDataAccessInterface channelUsersDataAccessObject, ShowSettingOutputBoundary showSettingPresenter) {
        this.pubnub= pubnub;
        this.channelUsersDataAccessObject = channelUsersDataAccessObject;
        this.showSettingPresenter = showSettingPresenter;
    }

    @Override
    public void execute(ShowSettingInputData showSettingInputData){
        showSettingInputData.getCurrentChannel();
        this.pubnub.hereNow()
            // tailor the next two lines to example
            .channels(Arrays.asList("coolChannel", "coolChannel2"))
            .includeUUIDs(true)
            .async(new PNCallback<PNHereNowResult>() {
                @Override
                public void onResponse(PNHereNowResult result, PNStatus status) {
                    if (status.isError()) {
                        // handle error
                        return;
                    }

                    for (PNHereNowChannelData channelData : result.getChannels().values()) {
                        System.out.println("---");
                        System.out.println("channel:" + channelData.getChannelName());
                        System.out.println("occupancy: " + channelData.getOccupancy());
                        System.out.println("occupants:");
                        for (PNHereNowOccupantData occupant : channelData.getOccupants()) {
                            System.out.println("uuid: " + occupant.getUuid() + " state: " + occupant.getState());
                        }
                    }
                }
            });

    }
}

package use_case.channelsetting;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;


import java.util.Arrays;

public class ShowSettingInteractor implements ShowSettingInputBoundary {
    final ShowSettingOutputBoundary showSettingPresenter;

    public ShowSettingInteractor(ShowSettingOutputBoundary showSettingPresenter) {
        this.showSettingPresenter = showSettingPresenter;
    }

    @Override
    public void execute(ShowSettingInputData showSettingInputData){
        String currentChannel = showSettingInputData.getCurrentChannel();
    }
}

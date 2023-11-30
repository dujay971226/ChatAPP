package use_case.setting.channelsetting;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;

import java.util.Arrays;
import java.util.Collections;

public class ShowSettingInteractor implements ShowSettingInputBoundary {
    final ShowSettingOutputBoundary showSettingPresenter;

    public ShowSettingInteractor(ShowSettingOutputBoundary showSettingPresenter) {
        this.showSettingPresenter = showSettingPresenter;
    }

    @Override
    public void execute(ShowSettingInputData showSettingInputData) {
        String currentChannel = showSettingInputData.getCurrentChannel();
        PubNub pubnub = showSettingInputData.getConfig();
        pubnub.hereNow()
                // tailor the next two lines to example
                .channels(Collections.singletonList(currentChannel))
                .includeState(true)
                .includeUUIDs(true)
                .async(new PNCallback<PNHereNowResult>() {
                    @Override
                    public void onResponse(PNHereNowResult result, PNStatus status) {
                        if (!status.isError()) {
                            ShowSettingOutputData showSettingOutputData = new ShowSettingOutputData(result.getChannels().values());
                            showSettingPresenter.prepareSuccessView(showSettingOutputData);
                            // handle error
                        } else {
                            showSettingPresenter.prepareFailView(status.getErrorData().toString());
                        }
                    }
                });
    }
}

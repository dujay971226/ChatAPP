package use_case.channelsetting;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;
import data_access.TokenDataAccessInterface;

import java.util.Arrays;

public class ShowSettingInteractor implements ShowSettingInputBoundary {
    final TokenDataAccessInterface pubNubDataAccessObject;
    final ShowSettingOutputBoundary showSettingPresenter;

    public ShowSettingInteractor(TokenDataAccessInterface pubNubDataAccessObject, ShowSettingOutputBoundary showSettingPresenter) {
        this.pubNubDataAccessObject = pubNubDataAccessObject;
        this.showSettingPresenter = showSettingPresenter;
    }

    @Override
    public void execute(ShowSettingInputData showSettingInputData){
        String currentChannel = showSettingInputData.getCurrentChannel();
        PubNub pubnub = this.pubNubDataAccessObject.getPubNubAccess();
        pubnub.hereNow()
            // tailor the next two lines to example
            .channels(Arrays.asList(currentChannel))
            .includeUUIDs(true)
            .async(new PNCallback<PNHereNowResult>() {
                @Override
                public void onResponse(PNHereNowResult result, PNStatus status) {
                    if (!status.isError()) {
                        ShowSettingOutputData showSettingOutputData = new ShowSettingOutputData(result.getChannels().values());
                        showSettingPresenter.prepareSuccessView(showSettingOutputData);
                        // handle error
                    } else{
                        showSettingPresenter.prepareFailView(status.getErrorData().toString());
                    }
                }
            });
    }
}

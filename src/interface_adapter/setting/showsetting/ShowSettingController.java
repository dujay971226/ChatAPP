package interface_adapter.setting.showsetting;

import com.pubnub.api.PubNub;
import use_case.setting.channelsetting.ShowSettingInputBoundary;
import use_case.setting.channelsetting.ShowSettingInputData;

public class ShowSettingController{
    private final ShowSettingInputBoundary showSettingInteractor;

    public ShowSettingController(ShowSettingInputBoundary showSettingInteractor) {
        this.showSettingInteractor = showSettingInteractor;
    }


    public void execute(String currentChannel, PubNub config) {
        ShowSettingInputData showSettingInputData = new ShowSettingInputData(currentChannel, config);

        showSettingInteractor.execute(showSettingInputData);
    }
}

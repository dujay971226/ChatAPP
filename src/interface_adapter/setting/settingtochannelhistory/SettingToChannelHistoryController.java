package interface_adapter.setting.settingtochannelhistory;


import com.pubnub.api.PubNub;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryInputBoundary;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryInputData;

public class SettingToChannelHistoryController{
    private final SettingToChannelHistoryInputBoundary returnToSettingInteractor;

    public SettingToChannelHistoryController(SettingToChannelHistoryInputBoundary returnToSettingInteractor) {
        this.returnToSettingInteractor = returnToSettingInteractor;
    }


    public void execute(String channelName, PubNub config) {
        SettingToChannelHistoryInputData returnToSettingInputData = new SettingToChannelHistoryInputData(channelName, config);
        returnToSettingInteractor.execute(returnToSettingInputData);
    }
}

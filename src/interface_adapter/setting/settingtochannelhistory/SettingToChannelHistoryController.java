package interface_adapter.setting.settingtochannelhistory;


import com.pubnub.api.PubNub;
import entity.User;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryInputBoundary;
import use_case.setting.settingtochannelhistory.SettingToChannelHistoryInputData;

public class SettingToChannelHistoryController {
    private final SettingToChannelHistoryInputBoundary returnToSettingInteractor;

    public SettingToChannelHistoryController(SettingToChannelHistoryInputBoundary returnToSettingInteractor) {
        this.returnToSettingInteractor = returnToSettingInteractor;
    }


    public void execute(String channelName, PubNub config, User user) {
        SettingToChannelHistoryInputData returnToSettingInputData = new SettingToChannelHistoryInputData(channelName, config, user);
        returnToSettingInteractor.execute(returnToSettingInputData);
    }
}

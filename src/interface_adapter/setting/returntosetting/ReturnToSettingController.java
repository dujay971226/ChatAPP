package interface_adapter.setting.returntosetting;

import com.pubnub.api.services.MessageActionService;
import entity.Message;
import use_case.setting.returntosetting.ReturnToSettingInputBoundary;
import use_case.setting.returntosetting.ReturnToSettingInputData;

import java.util.ArrayList;

public class ReturnToSettingController {
    private final ReturnToSettingInputBoundary returnToSettingInteractor;

    public ReturnToSettingController(ReturnToSettingInputBoundary returnToSettingInteractor) {
        this.returnToSettingInteractor = returnToSettingInteractor;
    }


    public void execute(ArrayList<Message> channelMessages) {
        ReturnToSettingInputData returnToSettingInputData = new ReturnToSettingInputData(channelMessages);
        returnToSettingInteractor.execute(returnToSettingInputData);
    }
}

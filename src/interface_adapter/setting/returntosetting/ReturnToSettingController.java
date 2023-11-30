package interface_adapter.setting.returntosetting;

import use_case.setting.returntosetting.ReturnToSettingInputBoundary;
import use_case.setting.returntosetting.ReturnToSettingInputData;

public class ReturnToSettingController {
    private final ReturnToSettingInputBoundary returnToSettingInteractor;

    public ReturnToSettingController(ReturnToSettingInputBoundary returnToSettingInteractor) {
        this.returnToSettingInteractor = returnToSettingInteractor;
    }


    public void execute() {
        ReturnToSettingInputData returnToSettingInputData = new ReturnToSettingInputData();
        returnToSettingInteractor.execute(returnToSettingInputData);
    }
}

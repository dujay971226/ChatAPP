package interface_adapter.showsetting;

import use_case.room.ShowSettingInputBoundary;
import use_case.room.ShowSettingInputData;

public class ShowSettingController {
    final ShowSettingInputBoundary showSettingInteractor;

    public ShowSettingController(ShowSettingInputBoundary showSettingInteractor) {
        this.showSettingInteractor = showSettingInteractor;
    }


    public void execute(String username, String password) {
        ShowSettingInputData showSettingInputData = new ShowSettingInputData();

        showSettingInteractor.execute(showSettingInputData);
    }
}

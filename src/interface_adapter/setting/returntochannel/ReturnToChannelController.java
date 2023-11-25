package interface_adapter.setting.returntochannel;

import use_case.setting.returntochannel.ReturnToChannelInputBoundary;
import use_case.setting.returntochannel.ReturnToChannelInputData;

public class ReturnToChannelController {
    private final ReturnToChannelInputBoundary returnToChannelInteractor;

    public ReturnToChannelController(ReturnToChannelInputBoundary returnToChannelInteractor) {
        this.returnToChannelInteractor = returnToChannelInteractor;
    }


    public void execute() {
        returnToChannelInteractor.execute(new ReturnToChannelInputData());
    }
}

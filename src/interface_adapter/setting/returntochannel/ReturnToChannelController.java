package interface_adapter.setting.returntochannel;

import entity.Message;
import use_case.setting.returntochannel.ReturnToChannelInputBoundary;
import use_case.setting.returntochannel.ReturnToChannelInputData;

import java.util.ArrayList;

public class ReturnToChannelController {
    private final ReturnToChannelInputBoundary returnToChannelInteractor;

    public ReturnToChannelController(ReturnToChannelInputBoundary returnToChannelInteractor) {
        this.returnToChannelInteractor = returnToChannelInteractor;
    }


    public void execute(ArrayList<Message> channelHistory) {
        returnToChannelInteractor.execute(new ReturnToChannelInputData(channelHistory));
    }
}

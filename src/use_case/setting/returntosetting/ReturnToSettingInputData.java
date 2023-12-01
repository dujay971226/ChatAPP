package use_case.setting.returntosetting;

import entity.Message;

import java.util.ArrayList;

public class ReturnToSettingInputData {
    private ArrayList<Message> channelMessages;
    public ReturnToSettingInputData(ArrayList<Message> channelMessages) {
        this.channelMessages = channelMessages;
    }

}

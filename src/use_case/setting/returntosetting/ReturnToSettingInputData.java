package use_case.setting.returntosetting;

import entity.Message;

import java.util.ArrayList;

public class ReturnToSettingInputData {
    private final ArrayList<Message> channelMessages;
    public ReturnToSettingInputData(ArrayList<Message> channelMessages) {
        this.channelMessages = channelMessages;
    }
    public ArrayList<Message> getChannelMessages() {
        return channelMessages;
    }
}

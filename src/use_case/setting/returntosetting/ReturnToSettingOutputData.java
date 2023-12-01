package use_case.setting.returntosetting;

import entity.Message;

import java.util.ArrayList;

public class ReturnToSettingOutputData {
    private final ArrayList<Message> channelMessages;
    public ReturnToSettingOutputData(ArrayList<Message> channelMessages) {
        this.channelMessages = channelMessages;
    }
    public ArrayList<Message> getChannelMessages() {
        return channelMessages;
    }
}

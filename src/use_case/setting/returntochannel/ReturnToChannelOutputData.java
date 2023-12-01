package use_case.setting.returntochannel;

import entity.Message;

import java.util.ArrayList;

public class ReturnToChannelOutputData {

    private final ArrayList<Message> channelHistory;

    public ReturnToChannelOutputData(ArrayList<Message> channelHistory) {
        this.channelHistory = channelHistory;
    }

    public ArrayList<Message> getChannelHistory() {
        return channelHistory;
    }
}

package use_case.channelsetting;

public class ShowSettingInputData {
    private String currentChannel;

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;
    }

    public ShowSettingInputData(String currentChannel) {
        this.currentChannel = currentChannel;
    }

    public String getCurrentChannel(){
        return this.currentChannel;
    }
}

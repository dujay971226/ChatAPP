package use_case.setting.showchannelhistory;

public interface ShowChannelHistoryOutputBoundary {
    void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData);
    void prepareFailView(String error);

}

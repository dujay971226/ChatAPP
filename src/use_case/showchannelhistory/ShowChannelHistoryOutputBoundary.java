package use_case.showchannelhistory;

public interface ShowChannelHistoryOutputBoundary {
    void prepareSuccessView(ShowChannelHistoryOutputData showChannelHistoryOutputData);
    void prepareFailView(String error);

}

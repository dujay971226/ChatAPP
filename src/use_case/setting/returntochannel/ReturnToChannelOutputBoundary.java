package use_case.setting.returntochannel;

public interface ReturnToChannelOutputBoundary {
    void prepareSuccessView(ReturnToChannelOutputData returnToChannelOutputData);
    void prepareFailView(String error);

}

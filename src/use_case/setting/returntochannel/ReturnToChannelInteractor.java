package use_case.setting.returntochannel;

public class ReturnToChannelInteractor implements ReturnToChannelInputBoundary {
    final ReturnToChannelOutputBoundary returnToChannelPresenter;

    public ReturnToChannelInteractor(ReturnToChannelOutputBoundary returnToChannelOutputBoundary) {
        this.returnToChannelPresenter = returnToChannelOutputBoundary;
    }

    // This use case meant to switch the view to the Room View and pass necessary data to Room State
    @Override
    public void execute(ReturnToChannelInputData returnToChannelInputData) {
        returnToChannelPresenter.prepareSuccessView(new ReturnToChannelOutputData(returnToChannelInputData.getChannelHistory()));
    }
}

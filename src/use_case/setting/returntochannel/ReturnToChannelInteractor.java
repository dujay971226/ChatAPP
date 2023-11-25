package use_case.setting.returntochannel;

public class ReturnToChannelInteractor implements ReturnToChannelInputBoundary {
    final ReturnToChannelOutputBoundary returnToChannelPresenter;

    public ReturnToChannelInteractor(ReturnToChannelOutputBoundary returnToChannelOutputBoundary){
        this.returnToChannelPresenter = returnToChannelOutputBoundary;
    }
    @Override
    public void execute(ReturnToChannelInputData returnToChannelInputData) {
        returnToChannelPresenter.prepareSuccessView(new ReturnToChannelOutputData());
    }
}

package setmessagefilter;
import com.pubnub.api.PubNub;
import data_access.TokenDataAccessInterface;

public class SetMessageFilterInteractor implements SetMessageFilterInputBoundary{

    final TokenDataAccessInterface pubNubDataAccessObject;
    final SetMessageFilterOutputBoundary setMessageFilterPresenter;

    public SetMessageFilterInteractor(TokenDataAccessInterface tokenDataAccessInterface, SetMessageFilterOutputBoundary setMessageFilterPresenter){
        this.pubNubDataAccessObject = tokenDataAccessInterface;
        this.setMessageFilterPresenter = setMessageFilterPresenter;
    }
    @Override
    public void execute(SetMessageFilterInputData setMessageFilterInputData) {
        PubNub pubnub = this.pubNubDataAccessObject.getPubNubAccess();

    }


}

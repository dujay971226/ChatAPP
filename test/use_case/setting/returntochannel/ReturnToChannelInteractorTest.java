package use_case.setting.returntochannel;

import com.pubnub.api.PubNubException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnToChannelInteractorTest {
    @Test
    public void successTest() throws PubNubException {

        ReturnToChannelInputData inputData = new ReturnToChannelInputData(new ArrayList<>());
        ReturnToChannelOutputBoundary successPresenter = new ReturnToChannelOutputBoundary() {
            @Override
            public void prepareSuccessView(ReturnToChannelOutputData outputData) {
                assertEquals(inputData.getChannelHistory(), outputData.getChannelHistory());
            }

            @Override
            public void prepareFailView(String error) {
                assert false;
            }
        };

        ReturnToChannelInputBoundary interactor = new ReturnToChannelInteractor(successPresenter);
        interactor.execute(inputData);
    }

}
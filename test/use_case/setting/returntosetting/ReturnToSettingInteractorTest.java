package use_case.setting.returntosetting;

import com.pubnub.api.PubNubException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnToSettingInteractorTest {
    @Test
    public void successTest() throws PubNubException {

        ReturnToSettingInputData inputData = new ReturnToSettingInputData(new ArrayList<>());
        ReturnToSettingOutputBoundary successPresenter = new ReturnToSettingOutputBoundary() {
            @Override
            public void prepareSuccessView(ReturnToSettingOutputData returnToSettingOutputData) {
                assertEquals(inputData.getChannelMessages(), returnToSettingOutputData.getChannelMessages());
            }

            @Override
            public void prepareFailView(String error) {
                assert false;
            }
        };

        ReturnToSettingInputBoundary interactor = new ReturnToSettingInteractor(successPresenter);
        interactor.execute(inputData);
    }

}
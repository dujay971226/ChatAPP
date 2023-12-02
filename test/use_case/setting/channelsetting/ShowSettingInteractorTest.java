package use_case.setting.channelsetting;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import entity.Channel;
import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowSettingInteractorTest {
    @Test
    public void successTest() throws PubNubException {
        User user = new User("user1", "password1");
        Channel channel = new Channel("channel1", user);
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(channel);
        UserId userId = new UserId(user.getName());
        PNConfiguration pnConfiguration = new PNConfiguration(userId);
        pnConfiguration.setSubscribeKey("sub-c-17a51508-3839-46d9-b8ee-b10b9b46bfa4");
        pnConfiguration.setPublishKey("pub-c-67b2c306-e615-4a3b-ae82-408ffd304abc");
        pnConfiguration.setSecretKey("sec-c-ZDU2ZDY5OGEtMDk5MC00MzZmLThiYWMtYzBkODI3MzY0YTk5");
        PubNub pubnub = new PubNub(pnConfiguration);

        ShowSettingInputData inputData = new ShowSettingInputData(channel.getName(), pubnub);
        ShowSettingOutputBoundary successPresenter = new ShowSettingOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowSettingOutputData showSettingOutputData) {
                assertEquals(user,showSettingOutputData.getChannelOccupancy());
            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        ShowSettingInputBoundary interactor = new ShowSettingInteractor(successPresenter);
        interactor.execute(inputData);
    }

}
package use_case.profile.profiletosubscribe;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import data_access.iChannelDataAccessObject;
import entity.Channel;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.profile.profiletocreate.ProfiletocreateInputData;
import use_case.profile.profiletocreate.ProfiletocreateInteractor;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProfiletosubscribeInteractorTest {
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

        ProfiletosubscribeInputData inputdata = new ProfiletosubscribeInputData(user,pubnub);
        ProfiletosubscribeOutputBoundary successPresenter = new ProfiletosubscribeOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfiletosubscribeOutputData outputData) {
                assertEquals(user,outputData.getUser());
                assertEquals(pubnub,outputData.getConfig());
                assertEquals(channels,outputData.getChannelLog());
            }

            @Override
            public void prepareFailView() {

            }
        };

        ProfiletosubscribeInteractor interactor = new ProfiletosubscribeInteractor(new iChannelDataAccessObject() {
            @Override
            public ArrayList<Channel> getChannels(User user) {
                return channels;
            }

            @Override
            public void save(String channelName, User curr) {

            }
        },successPresenter);
        interactor.execute(inputdata);


    }

}
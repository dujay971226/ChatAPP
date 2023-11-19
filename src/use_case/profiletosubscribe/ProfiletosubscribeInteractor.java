package use_case.profiletosubscribe;

import data_access.ChannelDataAccessInterface;
import entity.Channel;

import java.util.ArrayList;
import java.util.List;

public class ProfiletosubscribeInteractor implements ProfiletosubscribeInputBoundary{

    final ProfiletosubscribeOutputBoundary profilePresenter;
    final ChannelDataAccessInterface channelDataAccessObject;

    public ProfiletosubscribeInteractor(ChannelDataAccessInterface channelDataAccessObject, ProfiletosubscribeOutputBoundary profileOutputBoundary){
        this.channelDataAccessObject = channelDataAccessObject;
        this.profilePresenter = profileOutputBoundary;
    }

    public void execute(ProfiletosubscribeInputData inputdata) {
        List<Channel> channels = new ArrayList<>();
        channels = channelDataAccessObject.getChannels(inputdata.getUser().getname());

        ProfiletosubscribeOutputData profiletosubscribeOutputData= new ProfiletosubscribeOutputData(inputdata.getUser(), inputdata.getConfig(), channels);
        profilePresenter.prepareSuccessView(profiletosubscribeOutputData);
    }
}

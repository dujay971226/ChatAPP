package use_case.profile.profiletosubscribe;

import data_access.iChannelDataAccessObject;
import entity.Channel;

import java.util.ArrayList;

public class ProfiletosubscribeInteractor implements ProfiletosubscribeInputBoundary{

    final ProfiletosubscribeOutputBoundary profilePresenter;
    final iChannelDataAccessObject ichannelDataAccessObject;

    public ProfiletosubscribeInteractor(iChannelDataAccessObject ichannelDataAccessObject, ProfiletosubscribeOutputBoundary profileOutputBoundary){
        this.ichannelDataAccessObject = ichannelDataAccessObject;
        this.profilePresenter = profileOutputBoundary;
    }

    public void execute(ProfiletosubscribeInputData inputdata) {
        ArrayList<Channel> channels = new ArrayList<>();
        channels = ichannelDataAccessObject.getChannels(inputdata.getUser());

        ProfiletosubscribeOutputData profiletosubscribeOutputData= new ProfiletosubscribeOutputData(inputdata.getUser(), inputdata.getConfig(), channels);
        profilePresenter.prepareSuccessView(profiletosubscribeOutputData);
    }
}

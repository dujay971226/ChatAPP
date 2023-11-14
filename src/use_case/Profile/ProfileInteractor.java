package use_case.Profile;

import entity.User;

public class ProfileInteractor implements ProfileInputBoundary{
    final ProfileOutputBoundary profilePresenter;
    final ChannelDataAccessInterface channelDataAccessObject;

    public ProfileInteractor(ProfileOutputBoundary profilePresenter){
        this.profilePresenter = profilePresenter;
    }
    @Override
    public void execute(ProfileInputData inputdate) {
        //create room不用读---发信息
        //join room ----file read channel.json from channelfileascessobject.getchannels()--
    }
}

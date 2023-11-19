package app;

import data_access.ChannelDataAccessInterface;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Profile.profiletocreate.ProfiletocreateController;
import interface_adapter.Profile.profiletocreate.ProfiletocreatePresenter;
import interface_adapter.Profile.profiletosubscribe.ProfiletosubscribeController;
import interface_adapter.Profile.profiletosubscribe.ProfiletosubscribePresenter;
import interface_adapter.ViewManagerModel;
import use_case.Profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.Profile.profiletocreate.ProfiletocreateInteractor;
import use_case.Profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUsecaseFactory {

    public static ProfileView create(ViewManagerModel viewManagerModel, CreatRoomViewModel creatRoomViewModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessInterface channelDataAccessInterface){
        try {
            ProfiletocreateController profiletocreateController = createprofilecreateUseCase(viewManagerModel, creatRoomViewModel, profileViewModel);
            ProfiletosubscribeController profiletosubscribeController = createprofilesubscribeUsCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, channelDataAccessInterface);
            return new ProfileView(profileViewModel, profiletocreateController, profiletosubscribeController);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public ProfiletocreateController createprofilecreateUseCase(ViewManagerModel viewManagerModel, CreatRoomViewModel creatRoomViewModel, ProfileViewModel profileViewModel){
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfiletocreatePresenter(viewManagerModel, creatRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInteractor = new ProfiletocreateInteractor(profiletocreateOutputBoundary);
        return new ProfiletocreateController(profiletocreateInteractor);
    }

    public ProfiletosubscribeController createprofilesubscribeUsCase(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessInterface channelDataAccessInterface)throws IOException{
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfiletosubscribePresenter(viewManagerModel, subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profiletosubscribeInteractor = new ProfiletosubscribeInteractor(channelDataAccessInterface, profiletosubscribeOutputBoundary);
        return new ProfiletosubscribeController(profiletosubscribeInteractor);
    }

}

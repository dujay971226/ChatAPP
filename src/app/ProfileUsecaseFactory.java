package app;

import data_access.ChannelDataAccessInterface;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Profile.profiletocreate.ProfileToCreateController;
import interface_adapter.Profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.ViewManagerModel;
import use_case.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profiletocreate.ProfiletocreateInteractor;
import use_case.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUsecaseFactory {

    public static ProfileView create(ViewManagerModel viewManagerModel, CreatRoomViewModel creatRoomViewModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessInterface channelDataAccessInterface){
        try {
            ProfileToCreateController profiletocreateController = createprofilecreateUseCase(viewManagerModel, creatRoomViewModel, profileViewModel);
            ProfileToSubscribeController profiletosubscribeController = createprofilesubscribeUsCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, channelDataAccessInterface);
            return new ProfileView(profileViewModel, profiletocreateController, profiletosubscribeController);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public ProfileToCreateController createprofilecreateUseCase(ViewManagerModel viewManagerModel, CreatRoomViewModel creatRoomViewModel, ProfileViewModel profileViewModel){
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel, creatRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInteractor = new ProfiletocreateInteractor(profiletocreateOutputBoundary);
        return new ProfileToCreateController(profiletocreateInteractor);
    }

    public ProfileToSubscribeController createprofilesubscribeUsCase(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessInterface channelDataAccessInterface)throws IOException{
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfileToSubscribePresenter(viewManagerModel, subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profiletosubscribeInteractor = new ProfiletosubscribeInteractor(channelDataAccessInterface, profiletosubscribeOutputBoundary);
        return new ProfileToSubscribeController(profiletosubscribeInteractor);
    }

}

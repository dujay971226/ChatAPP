package app;

import data_access.ChannelDataAccessObject;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Profile.profiletocreate.ProfileToCreateController;
import interface_adapter.Profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.Profile.profiletologout.ProfileToLogoutController;
import interface_adapter.Profile.profiletologout.ProfileToLogoutPresenter;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.ViewManagerModel;
import use_case.Profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.Profile.profiletocreate.ProfiletocreateInteractor;
import use_case.Profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.Profile.profiletologout.LogoutInputBoundary;
import use_case.Profile.profiletologout.LogoutInteractor;
import use_case.Profile.profiletologout.LogoutOutputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.Profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUsecaseFactory {

    public static ProfileView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, SubscribeRoomViewModel subscribeRoomViewModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel, ChannelDataAccessObject channelDataAccessObject){
        try {
            ProfileToCreateController profileToCreateController = createProfileCreateUseCase(viewManagerModel, createRoomViewModel, profileViewModel);
            ProfileToSubscribeController profileToSubscribeController = createProfileSubscribeUseCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, channelDataAccessObject);
            ProfileToLogoutController profileToLogoutController = creatProfileLogoutUsecase(viewManagerModel,loginViewModel,profileViewModel);
            return new ProfileView(profileViewModel, profileToCreateController, profileToSubscribeController ,profileToLogoutController);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static ProfileToCreateController createProfileCreateUseCase(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, ProfileViewModel profileViewModel){
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel, createRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInteractor = new ProfiletocreateInteractor(profiletocreateOutputBoundary);
        return new ProfileToCreateController(profiletocreateInteractor);
    }

    public static ProfileToSubscribeController createProfileSubscribeUseCase(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessObject channelDataAccessObject)throws IOException{
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfileToSubscribePresenter(viewManagerModel, subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profiletosubscribeInteractor = new ProfiletosubscribeInteractor(channelDataAccessObject, profiletosubscribeOutputBoundary);
        return new ProfileToSubscribeController(profiletosubscribeInteractor);
    }

    public static ProfileToLogoutController creatProfileLogoutUsecase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new ProfileToLogoutPresenter(viewManagerModel,loginViewModel,profileViewModel);
        LogoutInputBoundary logoutInputBoundary = new LogoutInteractor(logoutOutputBoundary);
        return new ProfileToLogoutController(logoutInputBoundary);
    }

}

package app;

import data_access.ChannelDataAccessObject;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;
import interface_adapter.profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.profile.profiletologout.ProfileToLogoutController;
import interface_adapter.profile.profiletologout.ProfileToLogoutPresenter;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.profile.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profile.profiletocreate.ProfiletocreateInteractor;
import use_case.profile.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profile.profiletologout.LogoutInputBoundary;
import use_case.profile.profiletologout.LogoutInteractor;
import use_case.profile.profiletologout.LogoutOutputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profile.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUseCaseFactory {

    public static ProfileView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, SubscribeRoomViewModel subscribeRoomViewModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel, ChannelDataAccessObject channelDataAccessObject){
        try {
            ProfileToCreateController profileToCreateController = createProfileCreateUseCase(viewManagerModel, createRoomViewModel, profileViewModel);
            ProfileToSubscribeController profileToSubscribeController = createProfileSubscribeUseCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, channelDataAccessObject);
            ProfileToLogoutController profileToLogoutController = creatProfileLogoutUseCase(viewManagerModel,loginViewModel,profileViewModel);
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

    public static ProfileToLogoutController creatProfileLogoutUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new ProfileToLogoutPresenter(viewManagerModel,loginViewModel,profileViewModel);
        LogoutInputBoundary logoutInputBoundary = new LogoutInteractor(logoutOutputBoundary);
        return new ProfileToLogoutController(logoutInputBoundary);
    }

}

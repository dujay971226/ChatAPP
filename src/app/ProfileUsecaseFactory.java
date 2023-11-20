package app;

import data_access.ChannelDataAccessnObject;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Profile.profiletocreate.ProfileToCreateController;
import interface_adapter.Profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.Profile.profiletologout.ProfileToLogoutController;
import interface_adapter.Profile.profiletologout.ProfileToLogoutPresenter;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.Profile.profiletosubscribe.ProfileToSubscribePresenter;
import interface_adapter.ViewManagerModel;
import use_case.profiletocreate.ProfiletocreateInputBoundary;
import use_case.profiletocreate.ProfiletocreateInteractor;
import use_case.profiletocreate.ProfiletocreateOutputBoundary;
import use_case.profiletologout.LogoutInputBoundary;
import use_case.profiletologout.LogoutInteractor;
import use_case.profiletologout.LogoutOutputBoundary;
import use_case.profiletosubscribe.ProfiletosubscribeInputBoundary;
import use_case.profiletosubscribe.ProfiletosubscribeInteractor;
import use_case.profiletosubscribe.ProfiletosubscribeOutputBoundary;
import view.ProfileView;
import data_access.iChannelDataAccessObject;

import javax.swing.*;
import java.io.IOException;

public class ProfileUsecaseFactory {

    public static ProfileView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel, SubscribeRoomViewModel subscribeRoomViewModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel, iChannelDataAccessObject iChannelDataAccessnObject){
        try {
            ProfileToCreateController profiletocreateController = createProfileCreateUseCase(viewManagerModel, createRoomViewModel, profileViewModel);
            ProfileToSubscribeController profiletosubscribeController = createProfileSubscribeUseCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, iChannelDataAccessnObject);
            ProfileToLogoutController profileToLogoutController = creatrProfileLogoutUseCase(viewManagerModel, loginViewModel, profileViewModel);
            return new ProfileView(profileViewModel, profiletocreateController, profiletosubscribeController, profileToLogoutController);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static ProfileToCreateController createProfileCreateUseCase(ViewManagerModel viewManagerModel, CreateRoomViewModel creatRoomViewModel, ProfileViewModel profileViewModel){
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel, creatRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInteractor = new ProfiletocreateInteractor(profiletocreateOutputBoundary);
        return new ProfileToCreateController(profiletocreateInteractor);
    }

    public static ProfileToSubscribeController createProfileSubscribeUseCase(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, iChannelDataAccessObject iChannelDataAccessnObject)throws IOException{
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfileToSubscribePresenter(viewManagerModel, subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profileToSubscribeInteractor = new ProfiletosubscribeInteractor(iChannelDataAccessnObject, profiletosubscribeOutputBoundary);
        return new ProfileToSubscribeController(profileToSubscribeInteractor);
    }
    public static ProfileToLogoutController creatrProfileLogoutUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new ProfileToLogoutPresenter(viewManagerModel, loginViewModel, profileViewModel);
        LogoutInputBoundary profileToSubscribeInteractor = new LogoutInteractor(logoutOutputBoundary);
        return new ProfileToLogoutController(profileToSubscribeInteractor);
    }

}

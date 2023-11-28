package app;

import data_access.ChannelDataAccessObject;
import data_access.iChannelDataAccessObject;
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

/**
 * Factory for creating components of the profile view.
 * This class provides methods to create and assemble different controllers
 * for managing profile views in an application.
 * @author Xiaofeng Li
 */
public class ProfileUseCaseFactory {

    /**
     * Creates a ProfileView with all necessary controllers.
     * @param viewManagerModel The view manager model used for managing views.
     * @param createRoomViewModel The view model for creating rooms.
     * @param subscribeRoomViewModel The view model for subscribing to rooms.
     * @param loginViewModel The view model for login.
     * @param profileViewModel The view model for the profile view.
     * @param channelDataAccessObject The data access object for channel data.
     * @return A fully constructed ProfileView.
     */
    public static ProfileView create(ViewManagerModel viewManagerModel, CreateRoomViewModel createRoomViewModel,
                                     SubscribeRoomViewModel subscribeRoomViewModel, LoginViewModel loginViewModel,
                                     ProfileViewModel profileViewModel, ChannelDataAccessObject channelDataAccessObject){
        try {
            ProfileToCreateController profileToCreateController = createProfileCreateUseCase(viewManagerModel, createRoomViewModel, profileViewModel, channelDataAccessObject);
            ProfileToSubscribeController profileToSubscribeController = createProfileSubscribeUseCase(viewManagerModel, subscribeRoomViewModel, profileViewModel, channelDataAccessObject);
            ProfileToLogoutController profileToLogoutController = creatProfileLogoutUsecase(viewManagerModel,loginViewModel,profileViewModel);
            return new ProfileView(profileViewModel, profileToCreateController, profileToSubscribeController ,profileToLogoutController);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates a controller for managing profile creation use case.
     * @param viewManagerModel The view manager model.
     * @param createRoomViewModel The view model for creating rooms.
     * @param profileViewModel The profile view model.
     * @return A ProfileToCreateController for handling profile creation.
     */
    public static ProfileToCreateController createProfileCreateUseCase(ViewManagerModel viewManagerModel,
                                                                       CreateRoomViewModel createRoomViewModel,
                                                                       ProfileViewModel profileViewModel,
                                                                       iChannelDataAccessObject channelDataAccessObject){
        ProfiletocreateOutputBoundary profiletocreateOutputBoundary = new ProfileToCreatePresenter(viewManagerModel, createRoomViewModel, profileViewModel);
        ProfiletocreateInputBoundary profiletocreateInteractor = new ProfiletocreateInteractor(profiletocreateOutputBoundary, channelDataAccessObject);
        return new ProfileToCreateController(profiletocreateInteractor);
    }

    /**
     * Creates a controller for managing profile subscription use case.
     * @param viewManagerModel The view manager model.
     * @param subscribeRoomViewModel The view model for subscribing to rooms.
     * @param profileViewModel The profile view model.
     * @param channelDataAccessObject The data access object for channel data.
     * @return A ProfileToSubscribeController for handling profile subscriptions.
     * @throws IOException If there is an issue accessing channel data.
     */
    public static ProfileToSubscribeController createProfileSubscribeUseCase(ViewManagerModel viewManagerModel, SubscribeRoomViewModel subscribeRoomViewModel, ProfileViewModel profileViewModel, ChannelDataAccessObject channelDataAccessObject)throws IOException{
        ProfiletosubscribeOutputBoundary profiletosubscribeOutputBoundary = new ProfileToSubscribePresenter(viewManagerModel, subscribeRoomViewModel, profileViewModel);
        ProfiletosubscribeInputBoundary profiletosubscribeInteractor = new ProfiletosubscribeInteractor(channelDataAccessObject, profiletosubscribeOutputBoundary);
        return new ProfileToSubscribeController(profiletosubscribeInteractor);
    }

    /**
     * Creates a controller for managing profile logout use case.
     * @param viewManagerModel The view manager model.
     * @param loginViewModel The view model for login.
     * @param profileViewModel The profile view model.
     * @return A ProfileToLogoutController for handling profile logout.
     */
    public static ProfileToLogoutController creatProfileLogoutUsecase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ProfileViewModel profileViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new ProfileToLogoutPresenter(viewManagerModel,loginViewModel,profileViewModel);
        LogoutInputBoundary logoutInputBoundary = new LogoutInteractor(logoutOutputBoundary);
        return new ProfileToLogoutController(logoutInputBoundary);
    }

}

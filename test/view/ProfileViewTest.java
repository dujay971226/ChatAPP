package view;

import static org.junit.jupiter.api.Assertions.*;

import app.ProfileUseCaseFactory;
import data_access.ChannelDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreateController;
import interface_adapter.profile.profiletologout.ProfileToLogoutController;
import interface_adapter.profile.profiletosubscribe.ProfileToSubscribeController;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class ProfileViewTest {

    private ProfileView profileView;
    private ProfileViewModel profileViewModel;
    private ProfileToCreateController profileToCreateController;
    private ProfileToSubscribeController profileToSubscribeController;
    private ProfileToLogoutController profileToLogoutController;
    private ViewManagerModel viewManagerModel;
    private SubscribeRoomViewModel subscribeRoomViewModel;
    private CreateRoomViewModel createRoomViewModel;
    private LoginViewModel loginViewModel;
    private ChannelDataAccessObject channelDataAccessObject;

    @Before
    public void setUp() throws IOException {
        // 初始化实际的ViewModel和控制器
        profileViewModel = new ProfileViewModel();
        viewManagerModel = new ViewManagerModel();
        subscribeRoomViewModel = new SubscribeRoomViewModel();
        createRoomViewModel = new CreateRoomViewModel();
        loginViewModel = new LoginViewModel();
        channelDataAccessObject = new ChannelDataAccessObject("testchannel.json");
        profileView = ProfileUseCaseFactory.create(viewManagerModel,createRoomViewModel,subscribeRoomViewModel,loginViewModel,profileViewModel,channelDataAccessObject);
        viewManagerModel.setActiveView(profileView.getName());
    }

    @Test
    public void testButtonActions() {

        JButton createButton = profileView.getButtons().get(0);
        JButton subscribeButton = profileView.getButtons().get(1);
        JButton logoutButton = profileView.getButtons().get(2);

        createButton.doClick();
        assertEquals("create",viewManagerModel.getActiveView());
        subscribeButton.doClick();
        assertEquals("subscribe",viewManagerModel.getActiveView());
        logoutButton.doClick();
        assertEquals("log in",viewManagerModel.getActiveView());
    }

}

package interface_adapter.profile.profiletologout;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletocreate.ProfileToCreatePresenter;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileToLogoutPresenterTest {

    private ViewManagerModel mockViewManagerModel;
    private LoginViewModel loginViewModel;
    private ProfileViewModel mockProfileViewModel;
    private ProfileToLogoutPresenter presenter;

    @Before
    public void setUp() {
        mockViewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        mockProfileViewModel = new ProfileViewModel();

        presenter = new ProfileToLogoutPresenter(mockViewManagerModel, loginViewModel, mockProfileViewModel);
    }

    @Test
    public void prepareSuccessView_UpdatesViewModelAndViewManagerModel() {
        presenter.preparesuccessview();

        assertEquals("log in",mockViewManagerModel.getActiveView());
    }
}
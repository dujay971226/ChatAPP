package interface_adapter.profile.profiletosubscribe;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.profiletologout.ProfileToLogoutPresenter;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.profile.profiletosubscribe.ProfiletosubscribeOutputData;

import static org.junit.jupiter.api.Assertions.*;

class ProfileToSubscribePresenterTest {
    private ViewManagerModel mockViewManagerModel;
    private SubscribeRoomViewModel subscribeRoomViewModel;
    private ProfileViewModel mockProfileViewModel;
    private ProfileToSubscribePresenter presenter;

    @Before
    public void setUp() {
        mockViewManagerModel = new ViewManagerModel();
        subscribeRoomViewModel = new SubscribeRoomViewModel();
        mockProfileViewModel = new ProfileViewModel();

        presenter = new ProfileToSubscribePresenter(mockViewManagerModel, subscribeRoomViewModel, mockProfileViewModel);
    }

    @Test
    public void prepareSuccessView_UpdatesViewModelAndViewManagerModel() {
        ProfiletosubscribeOutputData profiletosubscribeOutputData = new ProfiletosubscribeOutputData(null,null,null);
        presenter.prepareSuccessView(profiletosubscribeOutputData);

        assertEquals("subscribe",mockViewManagerModel.getActiveView());
    }

}
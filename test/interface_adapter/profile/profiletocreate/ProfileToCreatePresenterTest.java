package interface_adapter.profile.profiletocreate;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomState;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.profile.ProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.profile.profiletocreate.ProfiletocreateOutputData;
import view.CreateRoomView;
import view.ProfileView;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileToCreatePresenterTest {

    private ViewManagerModel mockViewManagerModel;
    private CreateRoomViewModel mockCreateRoomViewModel;
    private ProfileViewModel mockProfileViewModel;
    private ProfileToCreatePresenter presenter;

    @Before
    public void setUp() {
        mockViewManagerModel = new ViewManagerModel();
        mockCreateRoomViewModel = new CreateRoomViewModel();
        mockProfileViewModel = new ProfileViewModel();

        presenter = new ProfileToCreatePresenter(mockViewManagerModel, mockCreateRoomViewModel, mockProfileViewModel);
    }

    @Test
    public void prepareSuccessView_UpdatesViewModelAndViewManagerModel() {
        ProfiletocreateOutputData outputData = new ProfiletocreateOutputData(null,null,null);

        presenter.prepareSuccessView(outputData);

        assertEquals("create",mockViewManagerModel.getActiveView());
    }
}
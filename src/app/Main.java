package app;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.endpoints.access.builder.GrantTokenBuilder;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.access_manager.v3.PNGrantTokenResult;
import com.pubnub.api.models.consumer.access_manager.v3.PNToken;
import data_access.create_room.ChannelDataAccessObject;
import data_access.create_room.CreateRoomDataAccessObject;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import use_case.create_room.CreateRoomDataAcessInterface;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import view.create_room.CreateRoomView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        JFrame application = new JFrame("ChatAPP");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CreateRoomViewModel viewModel = new CreateRoomViewModel();
        CreateRoomDataAcessInterface dataAccessObject = new CreateRoomDataAccessObject();
        CreateRoomOutputBoundary presenter = new CreateRoomPresenter(viewModel);
        CreateRoomInteractor interactor = new CreateRoomInteractor(dataAccessObject, presenter);
        CreateRoomController controller = new CreateRoomController(interactor);
        CreateRoomView view = new CreateRoomView(controller, viewModel);
        application.add(view);
        application.pack();
        application.setVisible(true);
    }


}

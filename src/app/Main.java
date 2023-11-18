package app;

import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.endpoints.access.builder.GrantTokenBuilder;
import com.pubnub.api.endpoints.access.builder.GrantTokenObjectsBuilder;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.access_manager.v3.PNGrantTokenResult;
import com.pubnub.api.models.consumer.access_manager.v3.PNToken;
import data_access.create_room.ChannelDataAccessObject;
import data_access.create_room.CreateRoomDataAccessObject;
import data_access.subscribe_room.SubscribeRoomDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomPresenter;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import use_case.create_room.CreateRoomDataAccessInterface;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;
import use_case.subscribe_room.SubscribeRoomInteractor;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import view.create_room.CreateRoomView;
import view.subscribe_room.SubscribeRoomView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws PubNubException {

        JFrame application = new JFrame("ChatAPP");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CreateRoomViewModel viewModel = new CreateRoomViewModel();
        CreateRoomDataAccessInterface dataAccessObject = new CreateRoomDataAccessObject("src/data_access/sampleData.json");
        CreateRoomOutputBoundary presenter = new CreateRoomPresenter(new ViewManagerModel(), viewModel, new RoomViewModel());
        CreateRoomInteractor interactor = new CreateRoomInteractor(dataAccessObject, presenter);
        CreateRoomController controller = new CreateRoomController(interactor);
        CreateRoomView view = new CreateRoomView(controller, viewModel);

        /*SubscribeRoomViewModel viewModel = new SubscribeRoomViewModel();
        SubscribeRoomDataAccessInterface dataAccessObject = new SubscribeRoomDataAccessObject();
        SubscribeRoomOutputBoundary presenter = new SubscribeRoomPresenter(viewModel);
        SubscribeRoomInteractor interactor = new SubscribeRoomInteractor(dataAccessObject, presenter);
        SubscribeRoomController controller = new SubscribeRoomController(interactor);
        SubscribeRoomView view = new SubscribeRoomView(controller, viewModel); */
        application.add(view);
        application.pack();
        application.setVisible(true);

    }



}

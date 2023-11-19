package app;

import com.pubnub.api.PubNubException;
import data_access.create_room.CreateRoomDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.room.RoomViewModel;
import use_case.create_room.CreateRoomDataAccessInterface;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import view.create_room.CreateRoomView;

import javax.swing.*;

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

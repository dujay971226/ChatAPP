package app;

import com.pubnub.api.PubNubException;
import data_access.create_room.CreateRoomDataAccessObject;
import data_access.subscribe_room.SubscribeRoomDataAccessObject;
import entity.Channel;
import entity.User;
import interface_adapter.ProfileToCreateController;
import interface_adapter.ProfileToSubscribeController;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomController;
import interface_adapter.create_room.CreateRoomPresenter;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.subscribe_room.SubscribeRoomController;
import interface_adapter.subscribe_room.SubscribeRoomPresenter;
import interface_adapter.subscribe_room.SubscribeRoomState;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import use_case.create_room.CreateRoomDataAccessInterface;
import use_case.create_room.CreateRoomInteractor;
import use_case.create_room.CreateRoomOutputBoundary;
import use_case.subscribe_room.SubscribeRoomDataAccessInterface;
import use_case.subscribe_room.SubscribeRoomInteractor;
import use_case.subscribe_room.SubscribeRoomOutputBoundary;
import view.create_room.CreateRoomView;
import view.subscribe_room.SubscribeRoomView;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws PubNubException {

        JFrame application = new JFrame("ChatAPP");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
/*
        CreateRoomViewModel viewModel = new CreateRoomViewModel();
        CreateRoomDataAccessInterface dataAccessObject = new CreateRoomDataAccessObject("src/data_access/sampleData.json");
        CreateRoomOutputBoundary presenter = new CreateRoomPresenter(new ViewManagerModel(), viewModel, new RoomViewModel());
        CreateRoomInteractor interactor = new CreateRoomInteractor(presenter);
        CreateRoomController controller = new CreateRoomController(interactor);
        ProfileToSubscribeController c = new ProfileToSubscribeController();
        CreateRoomView view = new CreateRoomView(controller, viewModel, c);
*/

        SubscribeRoomViewModel viewModel = new SubscribeRoomViewModel();
        SubscribeRoomState state = viewModel.getState();
        ArrayList<Channel> c = new ArrayList<>();
        c.add(new Channel("aal;sdkjf;alskf", new User("asdf", "asdf")));
        c.add(new Channel("b;lskdfja;j;lkj", new User("asdf", "asdf")));
        c.add(new Channel("c;lksadjf;laskdjfb;l", new User("asdf", "asdf")));
        c.add(new Channel("aal;sdkjf;alskf", new User("asdf", "asdf")));
        c.add(new Channel("b;lskdfja;j;lkj", new User("asdf", "asdf")));
        c.add(new Channel("c;lksadjf;laskdjfb;l", new User("asdf", "asdf")));
        c.add(new Channel("aal;sdkjf;alskf", new User("asdf", "asdf")));
        c.add(new Channel("b;lskdfja;j;lkj", new User("asdf", "asdf")));
        c.add(new Channel("c;lksadjf;laskdjfb;l", new User("asdf", "asdf")));
        c.add(new Channel("aal;sdkjf;alskf", new User("asdf", "asdf")));
        c.add(new Channel("b;lskdfja;j;lkj", new User("asdf", "asdf")));
        c.add(new Channel("c;lksadjf;laskdjfb;l", new User("asdf", "asdf")));
        c.add(new Channel("aal;sdkjf;alskf", new User("asdf", "asdf")));
        c.add(new Channel("b;lskdfja;j;lkj", new User("asdf", "asdf")));
        c.add(new Channel("c;lksadjf;laskdjfb;l", new User("asdf", "asdf")));
        state.setChannelLog(c);
        viewModel.setState(state);
        SubscribeRoomOutputBoundary presenter = new SubscribeRoomPresenter(new ViewManagerModel(), viewModel, new RoomViewModel());
        SubscribeRoomInteractor interactor = new SubscribeRoomInteractor(presenter);
        SubscribeRoomController controller = new SubscribeRoomController(interactor);
        SubscribeRoomView view = new SubscribeRoomView(controller, viewModel, new ProfileToCreateController());
        application.add(view);
        application.pack();
        application.setVisible(true);

    }



}

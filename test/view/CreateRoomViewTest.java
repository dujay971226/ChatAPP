package view;

import app.CreateRoomUseCaseFactory;
import com.pubnub.api.PubNubException;
import data_access.ChannelDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_room.CreateRoomViewModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.room.RoomViewModel;
import interface_adapter.setting.showsetting.SettingViewModel;
import interface_adapter.subscribe_room.SubscribeRoomViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateRoomViewTest {

    @Test
    void testInit() throws PubNubException, IOException {
        CreateRoomView view = CreateRoomUseCaseFactory.create(new ViewManagerModel(), new CreateRoomViewModel(),
                new RoomViewModel(),
                new SubscribeRoomViewModel(), new ProfileViewModel(), new JournalViewModel(), new SettingViewModel(),
                new ChannelDataAccessObject("./test/channel_test.json"));
    }
}
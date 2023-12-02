package view;

import app.JournalUsecaseFactory;
import com.pubnub.api.PubNubException;
import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.room.RoomViewModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JournalViewTest {

    private JournalView journalView;
    private JournalViewModel journalViewModel;
    private ViewManagerModel viewManagerModel;
    private RoomViewModel roomViewModel;

    @Before
    public void setUp() throws IOException, PubNubException {
        journalViewModel = new JournalViewModel();
        viewManagerModel = new ViewManagerModel();
        roomViewModel = new RoomViewModel();
        journalView = JournalUsecaseFactory.create(viewManagerModel,journalViewModel,roomViewModel);
        viewManagerModel.setActiveView(journalView.getName());
    }

    @Test
    public void testButtonActions() {

        JButton searchcontent = journalView.getButtons().get(0);
        JButton searchdoi = journalView.getButtons().get(1);
        JButton searchauthor = journalView.getButtons().get(2);
        JButton back = journalView.getButtons().get(3);
        String text = journalView.getText();
        searchcontent.doClick();
        String text1 = journalView.getText();
        assertNotEquals(text,text1);
        searchdoi.doClick();
        String text2 = journalView.getText();
        assertNotEquals(text1,text2);
        searchauthor.doClick();
        String text3 = journalView.getText();
        assertNotEquals(text2,text3);
        back.doClick();
        assertEquals(roomViewModel.getViewName(),viewManagerModel.getActiveView());
    }


}
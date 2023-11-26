package interface_adapter.journal.journaltoroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.room.RoomViewModel;
import use_case.journal.Content.JournalContentOutputData;
import use_case.journal.back.JournalToRoomOutputBoundary;

import javax.swing.*;

public class JournalToRoomPresenter implements JournalToRoomOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    private final RoomViewModel roomViewModel;
    public JournalToRoomPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel, RoomViewModel roomViewModel){
        this.journalViewModel = journalViewModel;
        this.viewManagerModel = viewManagerModel;
        this.roomViewModel = roomViewModel;
    }
    public void prepareRoomView(){
        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

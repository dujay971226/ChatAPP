package interface_adapter.journal.journaltoroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import use_case.journal.Content.JournalContentOutputData;
import use_case.journal.back.JournalToRoomOutputBoundary;

import javax.swing.*;

public class JournalToRoomPresenter implements JournalToRoomOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    public JournalToRoomPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
        this.journalViewModel = journalViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareRoomView(){
        viewManagerModel.setActiveView("room");
        journalViewModel.firePropertyChanged();
    }
}

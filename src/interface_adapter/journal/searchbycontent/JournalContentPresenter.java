package interface_adapter.journal.searchbycontent;

import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import use_case.journal.Content.JournalContentOutputBoundary;
import use_case.journal.Content.JournalContentOutputData;

import javax.swing.*;

public class JournalContentPresenter implements JournalContentOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    public JournalContentPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
         this.journalViewModel = journalViewModel;
         this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(JournalContentOutputData journalContentOutputData){
        JournalState newState = new JournalState();
        newState.setSearchResult(journalContentOutputData.getResult());
        journalViewModel.setState(newState);
        journalViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, journalContentOutputData.getResult());
    }
}

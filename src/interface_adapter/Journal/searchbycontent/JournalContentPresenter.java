package interface_adapter.Journal.searchbycontent;

import interface_adapter.Journal.JournalState;
import interface_adapter.Journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Journal.Content.JournalContentOutputBoundary;
import use_case.Journal.Content.JournalContentOutputData;

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

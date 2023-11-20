package interface_adapter.Journal.searchbydoi;

import interface_adapter.Journal.JournalState;
import interface_adapter.Journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Journal.Content.JournalContentOutputBoundary;
import use_case.Journal.Content.JournalContentOutputData;
import use_case.Journal.Doi.JournalDoiOutputBoundary;
import use_case.Journal.Doi.JournalDoiOutputData;

import javax.swing.*;

public class JournalDoiPresenter implements JournalDoiOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    public JournalDoiPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
         this.journalViewModel = journalViewModel;
         this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(JournalDoiOutputData journalDoiOutputData){
        JournalState newState = new JournalState();
        newState.setSearchResult(journalDoiOutputData.getResult());
        journalViewModel.setState(newState);
        journalViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, journalDoiOutputData.getResult());
    }


}

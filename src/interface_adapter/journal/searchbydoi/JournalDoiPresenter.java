package interface_adapter.journal.searchbydoi;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import use_case.journal.Doi.JournalDoiOutputBoundary;
import use_case.journal.Doi.JournalDoiOutputData;

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

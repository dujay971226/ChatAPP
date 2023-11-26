package interface_adapter.journal.searchbyissn;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import use_case.journal.Issn.JournalIssnOutputBoundary;
import use_case.journal.Issn.JournalIssnOutputData;

import javax.swing.*;

public class JournalIssnPresenter implements JournalIssnOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    public JournalIssnPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel){
         this.journalViewModel = journalViewModel;
         this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(JournalIssnOutputData journalIssnOutputData){
        JournalState newState = new JournalState();
        newState.setSearchResult(journalIssnOutputData.getResult());
        journalViewModel.setState(newState);
        journalViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, journalIssnOutputData.getResult());
    }
}

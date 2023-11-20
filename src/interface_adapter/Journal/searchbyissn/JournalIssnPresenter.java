package interface_adapter.Journal.searchbyissn;

import interface_adapter.Journal.JournalState;
import interface_adapter.Journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Journal.Content.JournalContentOutputBoundary;
import use_case.Journal.Content.JournalContentOutputData;
import use_case.Journal.Issn.JournalIssnOutputBoundary;
import use_case.Journal.Issn.JournalIssnOutputData;

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

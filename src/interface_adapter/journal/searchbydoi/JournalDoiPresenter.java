package interface_adapter.journal.searchbydoi;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import use_case.journal.Doi.JournalDoiOutputBoundary;
import use_case.journal.Doi.JournalDoiOutputData;

import javax.swing.*;

/**
 * Presenter for handling the presentation logic of DOI (Digital Object Identifier) operations in a journal.
 * This class implements the JournalDoiOutputBoundary and is responsible for
 * updating the view model based on the output data from the DOI processing logic.
 *
 * @author Xiaofeng Li
 */
public class JournalDoiPresenter implements JournalDoiOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;

    /**
     * Constructs a JournalDoiPresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param journalViewModel The view model for the journal view.
     */
    public JournalDoiPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel) {
        this.journalViewModel = journalViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares and updates the view to display the results of DOI processing.
     * @param journalDoiOutputData The output data from the DOI processing logic.
     */
    public void prepareSuccessView(JournalDoiOutputData journalDoiOutputData) {
        JournalState currState = journalViewModel.getState();
        String orig = currState.getSearchResult();
        currState.setSearchResult(orig + "\n" + "searching by doi:" + "\n" + journalDoiOutputData.getResult() + "\n");
        journalViewModel.setState(currState);
        journalViewModel.firePropertyChanged();
    }
}


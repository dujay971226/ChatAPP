package interface_adapter.journal.searchbyissn;

import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.ViewManagerModel;
import use_case.journal.Issn.JournalIssnOutputBoundary;
import use_case.journal.Issn.JournalIssnOutputData;

import javax.swing.*;

/**
 * Presenter for handling the presentation logic of ISSN (International Standard Serial Number) operations in a journal.
 * This class implements the JournalIssnOutputBoundary and is responsible for
 * updating the view model based on the output data from the ISSN processing logic.
 *
 * @author Xiaofeng Li
 */
public class JournalIssnPresenter implements JournalIssnOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;

    /**
     * Constructs a JournalIssnPresenter with the necessary view models.
     * @param viewManagerModel The model managing different views in the application.
     * @param journalViewModel The view model for the journal view.
     */
    public JournalIssnPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel) {
        this.journalViewModel = journalViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares and updates the view to display the results of ISSN processing.
     * @param journalIssnOutputData The output data from the ISSN processing logic.
     */
    public void prepareSuccessView(JournalIssnOutputData journalIssnOutputData) {
        JournalState currState = journalViewModel.getState();
        String orig = currState.getSearchResult();
        currState.setSearchResult(orig + "\n" + "searching by issn:" + "\n" + journalIssnOutputData.getResult() + "\n");
        journalViewModel.setState(currState);
        journalViewModel.firePropertyChanged();
    }
}


package interface_adapter.journal.journaltoroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalState;
import interface_adapter.journal.JournalViewModel;
import interface_adapter.room.RoomViewModel;
import use_case.journal.Content.JournalContentOutputData;
import use_case.journal.back.JournalToRoomOutputBoundary;

import javax.swing.*;

/**
 * Presenter for handling the transition from the journal view to the room view.
 * This class implements the JournalToRoomOutputBoundary and is responsible for preparing
 * and updating the view models involved in this transition.
 * @author Xiaofeng Li
 */
public class JournalToRoomPresenter implements JournalToRoomOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JournalViewModel journalViewModel;
    private final RoomViewModel roomViewModel;

    /**
     * Constructs a JournalToRoomPresenter with the necessary models.
     * @param viewManagerModel The model managing different views in the application.
     * @param journalViewModel The view model for the journal view.
     * @param roomViewModel The view model for the room view.
     */
    public JournalToRoomPresenter(ViewManagerModel viewManagerModel, JournalViewModel journalViewModel, RoomViewModel roomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.journalViewModel = journalViewModel;
        this.roomViewModel = roomViewModel;
    }

    /**
     * Prepares the room view for display. This method updates the active view in the
     * view manager model to the room view and triggers any necessary property changes.
     */
    public void prepareRoomView() {
        viewManagerModel.setActiveView(roomViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}


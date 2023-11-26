package interface_adapter.room.room_to_journal;

import interface_adapter.ViewManagerModel;
import interface_adapter.journal.JournalViewModel;
import use_case.room.room_to_journal.RoomToJournalOutputBoundary;

public class RoomToJournalPresenter implements RoomToJournalOutputBoundary {

    private final JournalViewModel journalViewModel;
    private final ViewManagerModel viewManagerModel;

    public RoomToJournalPresenter (ViewManagerModel viewManagerModel,
                                 JournalViewModel journalViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.journalViewModel = journalViewModel;
    }

    //Jump to profile view
    @Override
    public void prepareJournalView() {
        this.viewManagerModel.setActiveView(journalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

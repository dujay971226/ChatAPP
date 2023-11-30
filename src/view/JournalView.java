package view;

import interface_adapter.journal.JournalViewModel;
import interface_adapter.journal.journaltoroom.JournalToRoomController;
import interface_adapter.journal.searchbycontent.JournalContentController;
import interface_adapter.journal.searchbydoi.JournalDoiController;
import interface_adapter.journal.searchbyissn.AuthorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * The JournalView class represents the user interface for the journal-related functionality.
 * It allows users to search for journal content, DOI, and ISSN, as well as navigate back to the main room view.
 * This view includes input fields, buttons, and a text area to display search results.
 *
 * @author Xiaofeng Li
 */
public class JournalView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "journal";
    final JTextField contentInputField = new JTextField(15);
    final JTextField doiInputField = new JTextField(15);
    final JTextField authorInputField = new JTextField(15);
    private final JournalViewModel journalViewModel;
    private final JButton searchcontent;
    private final JButton searchdoi;
    private final JButton searchauthor;
    private final JButton back;
    private final JournalContentController journalContentController;
    private final JournalDoiController journalDoiController;
    private final AuthorController authorController;
    private final JournalToRoomController journalToRoomController;
    private final JTextArea textArea = new JTextArea(30, 100);


    JScrollPane jScrollPane = new JScrollPane(textArea);


    /**
     * Constructs a new JournalView instance.
     *
     * @param journalViewModel         The view model associated with this view.
     * @param journalContentController The controller for journal content search.
     * @param journalDoiController     The controller for DOI search.
     * @param authorController         The controller for author search.
     * @param journalToRoomController  The controller to navigate back to the main room view.
     */
    public JournalView(JournalViewModel journalViewModel, JournalContentController journalContentController, JournalDoiController journalDoiController, AuthorController authorController, JournalToRoomController journalToRoomController) {
        this.journalViewModel = journalViewModel;
        this.journalContentController = journalContentController;
        this.journalDoiController = journalDoiController;
        this.authorController = authorController;
        this.journalToRoomController = journalToRoomController;
        this.journalViewModel.addPropertyChangeListener(this);

        LabelTextPanel contentinfo = new LabelTextPanel(new JLabel("Content"), contentInputField);
        LabelTextPanel doiinfo = new LabelTextPanel(new JLabel("Doi"), doiInputField);
        LabelTextPanel authorinfo = new LabelTextPanel(new JLabel("author"), authorInputField);

        JPanel contentButton = new JPanel();
        searchcontent = new JButton(JournalViewModel.SEARCH_KEY_WORD_LABEL);
        contentButton.add(searchcontent);

        JPanel doiButton = new JPanel();
        searchdoi = new JButton(JournalViewModel.SEARCH_DOI_LABEL);
        doiButton.add(searchdoi);

        JPanel authorButton = new JPanel();
        searchauthor = new JButton(JournalViewModel.SEARCH_AUTHOR_LABEL);
        authorButton.add(searchauthor);

        back = new JButton(JournalViewModel.BACK_BUTTON_LABEL);
        authorButton.add(back);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        searchcontent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchcontent)) {
                    try {
                        journalContentController.execute(contentInputField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        searchdoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchdoi)) {
                    try {
                        journalDoiController.execute(doiInputField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        searchauthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchauthor)) {
                    try {
                        authorController.execute(authorInputField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    journalToRoomController.execute();
                }
            }
        });

        this.add(contentinfo);
        this.add(contentButton);
        this.add(doiinfo);
        this.add(doiButton);
        this.add(authorinfo);
        this.add(authorButton);
        this.add(jScrollPane);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Responds to property change events and updates the text area with search results.
     *
     * @param evt The property change event.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        textArea.setText(journalViewModel.getState().getSearchResult());
    }
}

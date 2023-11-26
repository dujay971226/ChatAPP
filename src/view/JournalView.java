package view;

import interface_adapter.journal.JournalViewModel;
import interface_adapter.journal.searchbycontent.JournalContentController;
import interface_adapter.journal.searchbydoi.JournalDoiController;
import interface_adapter.journal.searchbyissn.JournalIssnController;
import interface_adapter.journal.journaltoroom.JournalToRoomController;


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
    private final JournalViewModel journalViewModel;
    private final JButton searchcontent;
    private final JButton searchdoi;
    private final JButton searchissn;
    private final JButton back;
    final JTextField contentInputField = new JTextField(15);
    final JTextField doiInputField = new JTextField(15);
    final JTextField issnInputField = new JTextField(15);
    private final JournalContentController journalContentController;
    private final JournalDoiController journalDoiController;
    private final JournalIssnController journalIssnController;
    private final JournalToRoomController journalToRoomController;
    private final JTextArea textArea;

    /**
     * Constructs a new JournalView instance.
     *
     * @param journalViewModel      The view model associated with this view.
     * @param journalContentController The controller for journal content search.
     * @param journalDoiController  The controller for DOI search.
     * @param journalIssnController The controller for ISSN search.
     * @param journalToRoomController The controller to navigate back to the main room view.
     */
    public JournalView(JournalViewModel journalViewModel, JournalContentController journalContentController, JournalDoiController journalDoiController, JournalIssnController journalIssnController, JournalToRoomController journalToRoomController){
        this.journalViewModel = journalViewModel;
        this.journalContentController = journalContentController;
        this.journalDoiController = journalDoiController;
        this.journalIssnController = journalIssnController;
        this.journalToRoomController = journalToRoomController;
        this.journalViewModel.addPropertyChangeListener(this);

        LabelTextPanel contentinfo = new LabelTextPanel(new JLabel("Content"), contentInputField);
        LabelTextPanel doiinfo = new LabelTextPanel(new JLabel("Doi"), doiInputField);
        LabelTextPanel issninfo = new LabelTextPanel(new JLabel("Issn"), issnInputField);

        JPanel contentButton = new JPanel();
        searchcontent = new JButton(JournalViewModel.SEARCH_KEY_WORD_LABEL);
        contentButton.add(searchcontent);

        JPanel doiButton = new JPanel();
        searchdoi = new JButton(JournalViewModel.SEARCH_DOI_LABEL);
        doiButton.add(searchdoi);

        JPanel issnButton = new JPanel();
        searchissn = new JButton(JournalViewModel.SEARCH_ISSN_LABEL);
        issnButton.add(searchissn);

        back = new JButton(JournalViewModel.BACK_BUTTON_LABEL);
        issnButton.add(back);

        textArea = new JTextArea("");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        searchcontent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchcontent)){
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
                if (e.getSource().equals(searchdoi)){
                    try {
                        journalDoiController.execute(doiInputField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        searchissn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchissn)){
                    try {
                        journalIssnController.execute(issnInputField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)){
                    journalToRoomController.execute();
                }
            }
        });

        this.add(contentinfo);
        this.add(contentButton);
        this.add(doiinfo);
        this.add(doiButton);
        this.add(issninfo);
        this.add(issnButton);
        this.add(textArea);
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
    public void propertyChange(PropertyChangeEvent evt){
        textArea.setText(journalViewModel.getState().getSearchResult());
    }
}

package view;

import interface_adapter.journal.JournalViewModel;
import interface_adapter.journal.searchbycontent.JournalContentController;
import interface_adapter.journal.searchbydoi.JournalDoiController;
import interface_adapter.journal.searchbyissn.JournalIssnController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JournalView extends JPanel implements ActionListener {

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
    public JournalView(JournalViewModel journalViewModel, JournalContentController journalContentController, JournalDoiController journalDoiController, JournalIssnController journalIssnController){
        this.journalViewModel = journalViewModel;
        this.journalContentController = journalContentController;
        this.journalDoiController = journalDoiController;
        this.journalIssnController = journalIssnController;



        LabelTextPanel contentinfo = new LabelTextPanel(
                new JLabel("Content"), contentInputField);
        LabelTextPanel doiinfo = new LabelTextPanel(
                new JLabel("Doi"), doiInputField);
        LabelTextPanel issninfo = new LabelTextPanel(
                new JLabel("Issn"), issnInputField);

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

                }
            }
        });


         this.add(contentinfo);
         this.add(contentButton);

         this.add(doiinfo);
         this.add(doiButton);

         this.add(issninfo);
         this.add(issnButton);



    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

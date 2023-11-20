package view;

import javax.swing.*;

/**
 * A panel for text field with a label.
 */
public class LabelTextPanel extends JPanel {

    /**
     * Initializes a panel for text field with a label.
     * @param label label for text field
     * @param textField input text field
     */
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}

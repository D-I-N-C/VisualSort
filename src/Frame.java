import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JDialog implements ActionListener {

    int choice;
    JComboBox<String> comboBox;
    JButton btn;
    JLabel lbl;

    // constucter for Frame
    Frame() {
        // Settings for Frame
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        pack();
        setSize(300, 125);
        setLocationRelativeTo(null);
        // adding text to the window
        lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl);
        // adding a dropdown
        comboBox = new JComboBox<>(Sorter.ALGS);
        add(comboBox);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        // adding a button
        btn = new JButton("OK");
        add(btn);
        btn.addActionListener(this); // ActionListener to tell when button is pressed
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // saving selected option and disposes of current Frame
        if (e.getSource() == btn) {
            choice = comboBox.getSelectedIndex();
            dispose();
        }
    }
}

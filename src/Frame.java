import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JDialog implements ActionListener {

    int choice;
    JComboBox<String> comboBox;
    JButton btn;
    JLabel lbl;

    Frame() {
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        pack();
        setSize(300, 125);
        setLocationRelativeTo(null);

        lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl);

        comboBox = new JComboBox<>(Sorter.ALGS);
        add(comboBox);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn = new JButton("OK");
        add(btn);
        btn.addActionListener(this);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            choice = comboBox.getSelectedIndex();
            setLayout(null);
            remove(lbl);
            remove(btn);
            remove(comboBox);
            repaint();
            dispose();
        }
    }
}

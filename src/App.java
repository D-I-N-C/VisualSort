import java.awt.*;
import java.util.Collections;
import javax.swing.*;
import java.util.ArrayList;

public class App {
    static ShapeDrawing s;
    public static void main(String[] args) throws Exception {
        // promting for size of the arraylist
        int numberCount = Integer.parseInt(JOptionPane.showInputDialog("Choose array length"));
        // promting for delay between actions
        Sorter.timeout = Integer.parseInt(JOptionPane.showInputDialog("Choose delay time in milliseconds \n(between 5 - 25ms is best)"));
        // creating instance of JFrame with dropdown menu to select sorting ALG
        Frame fDialog = new Frame();
        // creating instance of JFrame for visulizer
        JFrame f = new JFrame();
        // settings for JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLACK);
        // passing info of contentPane to ShapeDrawing
        s = new ShapeDrawing(f.getContentPane(), numberCount);
        s.setPreferredSize(new Dimension(1500, 750));
        f.getContentPane().add(s);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        // calling the sorter based on user choice
        Sorter sorter = new Sorter();
        sorter.chosenSort(fDialog.choice, s.numbers, f, s);
    }
}

class ShapeDrawing extends JComponent {
    Container contentPane;
    int numberCount;
    ArrayList<Integer> numbers;
    boolean isSwapped;
    boolean isCompared;
    boolean isSorted;
    int sortedIndex;
    int index1;
    int index2;

    public ShapeDrawing(Container contentPane, int numberCount) {
        this.contentPane = contentPane;
        setNumberCount(numberCount);
        isSwapped = false;
        isCompared = false;
        isSorted = false;
    }

    public int getNumberCount() {
        return numberCount;
    }

    // populates ArrayList
    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
        numbers = new ArrayList<>(numberCount);
        for (int i = 0; i < numberCount; i++) {
            numbers.add(i + 1);
        }
        Collections.shuffle(numbers); // randomizes arraylist
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension dim = contentPane.getSize();
        // draws bars on screen and scales to window size
        for (int i = 0; i < numberCount; i += 1) {
            int x1 = dim.width * i / numberCount;
            int x2 = dim.width * (i + 1) / numberCount;
            int width = Math.max(1, x2 - x1);
            int h = dim.height * (numbers.get(i)) / (numberCount + 1);
            // colors of bars depending on if compared, swaped, sorted, or none
            if (isSorted && i <= sortedIndex) {
                g2.setColor(Color.GREEN);
            } else if (isSwapped && (i == index1 || i == index2)) {
                g2.setColor(Color.YELLOW);
            } else if (isCompared && (i == index1 || i == index2)) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.WHITE);
            }
            // drawing the bars
            g2.fillRect(x1, dim.height - h, width, h);
            if (numberCount <= dim.width / 5) { // dont draw outlines if the window isn't wide enough
                g2.setColor(Color.BLACK);
                g2.drawRect(x1, dim.height - h, width, h);
            }
        }

    }
}

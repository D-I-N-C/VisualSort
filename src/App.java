import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections; 
import javax.swing.*;
import java.util.ArrayList;
public class App {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame();//making instance of JFrame
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(Color.BLACK);
        f.setSize(500, 200);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code
    
        f.add(panel);
    
        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        //lbl.setVisible(true); // Not needed
    
        panel.add(lbl);
    
        String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3", "CHOICE 4","CHOICE 5", "CHOICE 6" };
    
        final JComboBox<String> cb = new JComboBox<String>(choices);
    
        cb.setMaximumSize(cb.getPreferredSize()); // added code
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        panel.add(cb);
    
        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
        panel.add(btn);
    
        f.setVisible(true); // added code

        
        //selection = cb.getSelectedItem
        //return selection upon button press (action listener)
        //promting for size of the arraylist
        int numberCount = Integer.parseInt(JOptionPane.showInputDialog("Select the Size of  the Array"));
        //settings for JFrame
        ShapeDrawing s = new ShapeDrawing (f.getContentPane(), numberCount);
        s.setPreferredSize(new Dimension(1500, 750));
        f.getContentPane().add(s);
        f.pack();   
        
        Sorter.bubbleSort(s.numbers, f, s);
        
        
    }
    
}

// class buttonPressThing implements ActionListener {
//     Button b;
//     b.addActionListener(this);
//     public void actionPerformed(ActionEvent e){
//         int selection = cb.getSelectedItem
         //make dropdown and button in this class, implment action listener for button to get dropdown option
//     }
// } 


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

    public ShapeDrawing(Container contentPane,int numberCount){
        this.contentPane = contentPane;
        setNumberCount(numberCount);
        isSwapped = false;
        isCompared = false;
        isSorted = false;
    }
    
    public int getNumberCount(){
        return numberCount;
    }

    public void setNumberCount(int numberCount){
        this.numberCount = numberCount;
        numbers=new ArrayList<>(numberCount);
        for (int i = 0; i < numberCount; i++) {
            numbers.add(i+1);
        }
        Collections.shuffle(numbers); //randomizes arraylist
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Dimension dim = contentPane.getSize();
        //draws bars on screen and scales to window size
        for(int i = 0; i < numberCount ; i += 1){
            int x1 = dim.width*i/numberCount;
            int x2 = dim.width*(i+1)/numberCount;
            int width = Math.max(1,x2-x1);
            int h = dim.height*(numbers.get(i))/(numberCount + 1);
            if (isSorted && i <= sortedIndex) {
                g2.setColor(Color.GREEN);
            }
            else if (isSwapped && (i==index1 || i==index2)) {
                g2.setColor(Color.RED);
            }
            else if (isCompared && (i==index1 || i==index2)) {
                g2.setColor(Color.YELLOW);
            }
            else{
                g2.setColor(Color.WHITE);
            }
            
            g2.fillRect(x1, dim.height - h, width, h);
            if (numberCount <= dim.width/5) { //dont draw outlines if the window isn't wide enough
                g2.setColor(Color.BLACK);
                g2.drawRect(x1, dim.height - h,  width, h);                     
            }
        }
        
    }
}

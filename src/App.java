import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.Collections; 
import javax.swing.*;
import java.util.ArrayList;
public class App {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame();//making instance of JFrame
        f.setDefaultCloseOperation(3);
        //promting for size of the arraylist
        int numberCount = Integer.parseInt(JOptionPane.showInputDialog("ENTER"));
        //settings for JFrame
        f.getContentPane().setBackground(Color.BLACK);
        ShapeDrawing s = new ShapeDrawing (f.getContentPane(), numberCount);
        s.setPreferredSize(new Dimension(1500, 750));
        f.getContentPane().add(s);
        f.pack();   
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Sorter.bubbleSort(s.numbers, f);

    }

}
class ShapeDrawing extends JComponent {
    Container contentPane;
    int numberCount;
    ArrayList<Integer> numbers;

    public ShapeDrawing(Container contentPane,int numberCount){
        this.contentPane = contentPane;
        setNumberCount(numberCount);
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
            g2.setColor(Color.WHITE);
            g2.fillRect(x1, dim.height - h, width, h);
            if (numberCount <= dim.width/5) { //dont draw outlines if the window isn't wide enough
                g2.setColor(Color.BLACK);
                g2.drawRect(x1, dim.height - h,  width, h);                     
            }
        }
        
    }
}

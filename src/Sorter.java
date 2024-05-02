import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Sorter{

    public static void bubbleSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s)throws Exception{
        boolean changed = true;

        while(changed) {
            changed = false;
            for (int i = 0; i < numbers.size() - 1; i++) {
                s.isSwapped = false;
                s.isCompared = false;
                if (isGreaterThan(numbers, i, i + 1, s) == true) {
                    swap(numbers, i, i + 1, s);
                    changed = true;
                }
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(10);                       
            }
        }
        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
    
    public static void swap(ArrayList<Integer> numbers,int index1, int index2, ShapeDrawing s){
        int temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2,temp);
        s.isSwapped = true;
        s.index1 = index1;
        s.index2 = index2;
    }

    public static boolean isGreaterThan(ArrayList<Integer> numbers, int index1, int index2, ShapeDrawing s){
        s.isCompared = true;
        s.index1 = index1;
        s.index2 = index2;
        return(numbers.get(index1)>numbers.get(index2));
    }

}
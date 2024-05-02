import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;

public class Sorter{

    public static void bubbleSort(List<Integer> numbers, JFrame f)throws Exception{
        boolean changed = true;

        while(changed) {
            changed = false;
            for (int i = 0; i < numbers.size() - 1; i++) {
                if (isGreaterThan(numbers, i, i + 1) == true) {
                    swap(numbers, i, i + 1);
                    changed = true;
                    f.repaint();
                    TimeUnit.MILLISECONDS.sleep(60);                    
                }
            }
        }
    }
    
    public static void swap(List<Integer> numbers,int index1, int index2){
        int temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2,temp);
    }

    public static boolean isGreaterThan(List<Integer> numbers, int index1, int index2){      
        return(numbers.get(index1)>numbers.get(index2));
    }
}
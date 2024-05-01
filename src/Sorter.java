import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Sorter{

    public void swap(ArrayList<Integer> numbers,int index1, int index2){
        int temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2,temp);
    }

    public boolean compare(ArrayList<Integer> numbers, int index1, int index2){      
        return(index2<index1);
    }
    
}
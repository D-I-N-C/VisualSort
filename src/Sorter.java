import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;


public class Sorter{
    static JButton btn;
    // Delay btween actions in milliseconds
    static int timeout = 10;
    // Avalible options of sorting AGLS for dropdown menu
    final static String[] ALGS = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Heap Sort", "BOGO Sort" };
    // runs sorting ALG based on user choice
    public void chosenSort(int choice, ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
        switch (choice) {
            case 0:
                bubbleSort(s.numbers, f, s);
                break;

            case 1:
                selectionSort(s.numbers, f, s);
                break;

            case 2:
                insertionSort(s.numbers, f, s);
                break;

            case 3:
                heapSort(s.numbers, f, s);
                break;

            case 4:
                bogoSort(s.numbers, f, s);
                break;

            default: 
                break;
        }
    }

    /*
     *implements bubble sort 
     *scans left to right over the array
     *swaping numbers if the number to the right
     *of the current index is smaller than itself
     */ 
    public void bubbleSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
        boolean changed = true;

        while (changed) {
            changed = false;
            for (int i = 0; i < numbers.size() - 1; i++) {
                s.isSwapped = false;
                s.isCompared = false;
                if (isGreaterThan(numbers, i, i + 1, s)) {
                    swap(numbers, i, i + 1, s);
                    changed = true;
                }
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
            }
        }

        sorted(numbers, s, f);
    }

    /*
     * implements selection sort
     * scrolls through each element of the array, and finds the lowest value
     * the lowest value of the original array is put into the next position of sorted portion of the array
     * (no new array is created, but rather the array is divided into an unsorted and sorted part)
     * Repeat until sorted
     */
    public void selectionSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

        for (int i = 0; i < numbers.size() - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < numbers.size(); j++) {
                s.isSwapped = false;
                if (isGreaterThan(numbers, minIndex, j, s)) {
                    s.isCompared = false;
                    minIndex = j;
                }
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
            }

            swap(numbers, i, minIndex, s);

            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }

        sorted(numbers, s, f);
    }

    /*
     *implements insertion sort 
     * scrolls through each element from the unsorted original array and "inserts" it into its respective place on the a new sorted list.
     * repeat this process until there are no values left in the original unsorted portion
     * (no new array is created, but rather the array is divided into an unsorted and sorted part)
     */
    public void insertionSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

        int i = 1;
        while (i < numbers.size()) {
            int j = i;
            while (j > 0) {
                boolean result = isGreaterThan(numbers, j - 1, j, s);
                if (!result) {
                    s.isCompared = true;
                    f.repaint();
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    s.isCompared = false;
                    break;
                }
                swap(numbers, j, j - 1, s);
                j -= 1;
                s.isSwapped = true;
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
                s.isSwapped = false;
            }
            i += 1;
        }

        sorted(numbers, s, f);
    }
    /*
     * implements heap sort
     * idk how this works :D
     * https://en.wikipedia.org/wiki/Heapsort
     */
    public void heapSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

        int start = numbers.size() / 2;
        int end = numbers.size();
        while (end > 1) {
            s.isSwapped = false;
            s.isCompared = false;
            if (start > 0) {
                start--;
            } else {
                end--;
                swap(numbers, end, 0, s);
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
            }

            s.isSwapped = false;
            int root = start;
            while (2 * root + 1 < end) {
                s.isSwapped = false;
                s.isCompared = false;
                int child = 2 * root + 1;
                if (child + 1 < end && isGreaterThan(numbers, child + 1, child, s)) {
                    child++;
                }
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
                if (isGreaterThan(numbers, child, root, s)) {
                    s.isCompared = false;
                    swap(numbers, root, child, s);
                    f.repaint();
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    root = child;
                } else {
                    f.repaint();
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    break;
                }
            }
        }

        sorted(numbers, s, f);
    }

    /*
     * implements BOGO sort (stuupid sort)
     * Randomizes the array until sorted
     */
    public void bogoSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
        boolean sorted = false;

        while (s.isSorted == false) {

            for (int i = 0; i < numbers.size() - 1; i++) {
                s.isCompared = false;
                if (isGreaterThan(numbers, i, i + 1, s)) {
                    sorted = false;
                    break;
                }
                else sorted = true;
                f.repaint();
                TimeUnit.MILLISECONDS.sleep(timeout);
            }

            if (sorted == true) break;
                
            Collections.shuffle(numbers);
            f.repaint();
        }

        sorted(numbers, s, f);
    }

    //swaping funtion
    public static void swap(ArrayList<Integer> numbers, int index1, int index2, ShapeDrawing s) {
        int temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2, temp);
        s.isSwapped = true; // flag to recolor the bar
        s.index1 = index1;
        s.index2 = index2;
    }
    //comparison funtion
    public static boolean isGreaterThan(ArrayList<Integer> numbers, int index1, int index2, ShapeDrawing s) {
        s.isCompared = true; // flag to recolor the bar
        s.index1 = index1;
        s.index2 = index2;
        return (numbers.get(index1) > numbers.get(index2));
    }
    
    public void sorted(ArrayList<Integer> numbers, ShapeDrawing s, JFrame f) throws Exception{
        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }
} 

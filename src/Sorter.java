import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collections;

public class Sorter {

    static int timeout = 50;
    final static String[] ALGS = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Heap Sort", "BOGO Sort" };

    public static void chosenSort(int choice, ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
        switch (choice) {
            case 0:
                Sorter.bubbleSort(s.numbers, f, s);
                break;

            case 1:
                Sorter.selectionSort(s.numbers, f, s);
                break;

            case 2:
                Sorter.insertionSort(s.numbers, f, s);
                break;

            case 3:
                Sorter.heapSort(s.numbers, f, s);
                break;

            case 4:
                Sorter.bogoSort(s.numbers, f, s);
                break;

            default:
                break;
        }
    }

    public static void bubbleSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
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
        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }

    public static void selectionSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

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

        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }

    public static void insertionSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

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

        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int k = 0; k < numbers.size(); k++) {
            s.sortedIndex = k;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }

    public static void heapSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {

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

        s.isSorted = true;
        s.isSwapped = false;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }

    public static void bogoSort(ArrayList<Integer> numbers, JFrame f, ShapeDrawing s) throws Exception {
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

        s.isSorted = true;
        s.isCompared = false;
        for (int i = 0; i < numbers.size(); i++) {
            s.sortedIndex = i;
            f.repaint();
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
    }

    public static void swap(ArrayList<Integer> numbers, int index1, int index2, ShapeDrawing s) {
        int temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2, temp);
        s.isSwapped = true;
        s.index1 = index1;
        s.index2 = index2;
    }

    public static boolean isGreaterThan(ArrayList<Integer> numbers, int index1, int index2, ShapeDrawing s) {
        s.isCompared = true;
        s.index1 = index1;
        s.index2 = index2;
        return (numbers.get(index1) > numbers.get(index2));
    }

}
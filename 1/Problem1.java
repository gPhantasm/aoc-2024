import java.io.*;
import java.util.ArrayList;

public class Problem1 {

    // quicksort methods
    public static int partition(int[] arr, int low, int high) {

        int piv = arr[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < piv) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);

            quicksort(arr, low, part - 1);
            quicksort(arr, part + 1, high);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // hardcoded arrays because i didn't want to read the size
        // or use other data structs lol

        int[] list1 = new int[1000];
        int[] list2 = new int[1000];

        try {
            String file = (args.length > 0) ? args[0] : "input.txt";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            // parse ints broken by the three spaces in each line
            int i = 0;
            while ((line = br.readLine()) != null) {
                // trim whitespace + split by any amount of whitespace
                String[] split = line.trim().split("\\s+");
                list1[i] = (Integer.parseInt(split[0]));
                list2[i] = (Integer.parseInt(split[1]));
                ++i;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file!\n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IOException:\n" + e);
            System.exit(1);
        }

        int sum = 0;

        quicksort(list1, 0, list1.length - 1);
        System.out.println("Sorted 1");
        quicksort(list2, 0, list2.length - 1);
        System.out.println("Sorted 2");

        for (int i = 0; i < list1.length; i++) {
            System.out.printf("Distance between %d and %d is %d\n", list1[i], list2[i], Math.abs(list1[i] - list2[i]));
            sum += Math.abs(list1[i] - list2[i]);
        }

        System.out.println("The total distance is " + sum);
    }
}
import java.io.*;
import java.util.HashMap;

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

    // read the input from a file
    public static void populate(String[] args, int[] list1, int[] list2) throws FileNotFoundException, IOException {
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
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // hardcoded arrays because i didn't want to read the size
        // or use other data structs lol

        int[] list1 = new int[1000];
        int[] list2 = new int[1000];

        // PART 1 -- distance via sum of diffs

        populate(args, list1, list2);

        int sum = 0;

        quicksort(list1, 0, list1.length - 1);
        System.out.println("Sorted left nums");
        quicksort(list2, 0, list2.length - 1);
        System.out.println("Sorted right nums");

        for (int i = 0; i < list1.length; i++)
            sum += Math.abs(list1[i] - list2[i]);

        System.out.printf("***PART 1***\n\tThe total distance is %d\n", sum);

        // PART 2 -- similarity score

        HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        int similarity = 0;

        for (int i = 0; i < list2.length; i++) {
            if (occurrences.get(list2[i]) == null)
                occurrences.put(list2[i], 1);
            else
                occurrences.put(list2[i], occurrences.get(list2[i]) + 1);
        }

        for (int i = 0; i < list1.length; i++) {
            if (occurrences.get(list1[i]) != null) {
                System.out.printf("\nFound %d has occurred %d times\n", list1[i], occurrences.get(list1[i]));
                similarity += list1[i] * occurrences.get(list1[i]);
            }
        }

        System.out.printf("***PART 2***\n\tThe similarity score is %d\n", similarity);
    }
}
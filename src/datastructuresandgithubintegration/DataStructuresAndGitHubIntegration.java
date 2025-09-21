package datastructuresandgithubintegration;

import java.util.Scanner;

public class DataStructuresAndGitHubIntegration {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = 5;
            int[] arr = new int[n];

            System.out.println("Enter " + n + " different numbers (press Enter after each):");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Number " + (i + 1) + ": ");
                    if (sc.hasNextInt()) {
                        arr[i] = sc.nextInt();
                        break;
                    } else {
                        System.out.print("Invalid input. Please enter an integer. ");
                        sc.next(); // clear token
                    }
                }
            }

            // Ask user for sorting algorithm choice
            System.out.println("\nChoose sorting algorithm (for display) :");
            System.out.println("1 - Selection Sort");
            System.out.println("2 - Bubble Sort");
            System.out.println("3 - Insertion Sort");
            System.out.print("Enter choice (1/2/3): ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> selectionSort(arr);
                case 2 -> bubbleSort(arr);
                case 3 -> insertionSort(arr);
                default -> {
                    System.out.println("Invalid choice, using Selection Sort by default.");
                    selectionSort(arr);
                }
            }

            System.out.println("\nSorted array (ascending):");
            printArray(arr);

            // Find second lowest and second highest distinct values WITHOUT using built-in sorting
            Integer secondLowest = secondLowestDistinctUnsorted(arr);
            Integer secondHighest = secondHighestDistinctUnsorted(arr);

            if (secondLowest != null) {
                System.out.println("Second lowest (distinct) number: " + secondLowest);
            } else {
                System.out.println("Second lowest (distinct) not found (less than 2 distinct values).");
            }

            if (secondHighest != null) {
                System.out.println("Second highest (distinct) number: " + secondHighest);
            } else {
                System.out.println("Second highest (distinct) not found (less than 2 distinct values).");
            }
        }
    }

    // Selection Sort
    private static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }

    // Bubble Sort
    private static void bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    // Insertion Sort
    private static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Utility: Print array
    private static void printArray(int[] a) {
        for (int x : a) System.out.print(x + " ");
        System.out.println();
    }

    /**
     * Find second lowest distinct value in an unsorted array without built-in sort.
     * Returns null if there are fewer than two distinct values.
     */
    private static Integer secondLowestDistinctUnsorted(int[] a) {
        if (a == null || a.length < 2) return null;

        // We'll find smallest and second smallest distinct values in one pass.
        Integer smallest = null;
        Integer secondSmallest = null;

        for (int value : a) {
            if (smallest == null || value < smallest) {
                // new smallest found: shift smallest -> secondSmallest
                if (smallest == null || value != smallest) {
                    secondSmallest = smallest;
                }
                smallest = value;
            } else if (value != smallest) {
                // value > smallest
                if (secondSmallest == null || value < secondSmallest) {
                    secondSmallest = value;
                }
            }
            // if value equals smallest or secondSmallest, ignore duplicates
        }
        return secondSmallest;
    }

    /**
     * Find second highest distinct value in an unsorted array without built-in sort.
     * Returns null if there are fewer than two distinct values.
     */
    private static Integer secondHighestDistinctUnsorted(int[] a) {
        if (a == null || a.length < 2) return null;

        Integer largest = null;
        Integer secondLargest = null;

        for (int value : a) {
            if (largest == null || value > largest) {
                // shift largest -> secondLargest
                if (largest == null || value != largest) {
                    secondLargest = largest;
                }
                largest = value;
            } else if (value != largest) {
                // value < largest
                if (secondLargest == null || value > secondLargest) {
                    secondLargest = value;
                }
            }
        }
        return secondLargest;
    }
}
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

            // Sort using selection sort (ascending)
            selectionSort(arr);

            System.out.println("\nSorted array (ascending):");
            printArray(arr);

            // Find second lowest and second highest distinct values
            Integer secondLowest = findSecondLowestDistinct(arr);
            Integer secondHighest = findSecondHighestDistinct(arr);

            if (secondLowest != null) {
                System.out.println("Second lowest (distinct) number: " + secondLowest);
            } else {
                System.out.println("Second lowest (distinct) not found (all elements may be equal).");
            }

            if (secondHighest != null) {
                System.out.println("Second highest (distinct) number: " + secondHighest);
            } else {
                System.out.println("Second highest (distinct) not found (all elements may be equal).");
            }
        }
    }

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

    private static void printArray(int[] a) {
        for (int x : a) System.out.print(x + " ");
        System.out.println();
    }

    private static Integer findSecondLowestDistinct(int[] a) {
        if (a.length < 2) return null;
        int lowest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > lowest) return a[i];
        }
        return null;
    }

    private static Integer findSecondHighestDistinct(int[] a) {
        if (a.length < 2) return null;
        int highest = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < highest) return a[i];
        }
        return null;
    }
}   

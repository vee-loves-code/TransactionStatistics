package com.example.transactiostatistics.algorithm;

import java.util.*;

public class AlgorithmSolutions {

    public static boolean checkIfTwoIntegersSumToValue(int[] array, int value) {
        //Create a hash set to store previously seen integers.
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            //Iterate through the array of integers to calculate the difference between the target sum and the current integer.
            int difference = value - num;
            //Check if the difference is present in the hash set.
            if (set.contains(difference)) {
                return true;
            }
            //If the difference is not present, add the current integer to the hash set.
            set.add(num);
        }
        return false;
    }

    public static int[] findLowHighIndices(int[] array, int key) {
        //Create two variables to store the low and high indices. Initialize them to -1.
        int low = findLowIndex(array, key);
        int high = findHighIndex(array, key);
        return new int[] {low, high};
    }

    // find the low index of the key
    private static int findLowIndex(int[] array, int key) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == key) {
                result = mid;
                high = mid - 1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // find the high index of the key
    private static int findHighIndex(int[] array, int key) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == key) {
                result = mid;
                low = mid + 1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> result = new ArrayList<>();
        //Create list to store the merged intervals.
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        //Iterate through the input intervals, If the merged list is empty, add the current interval to the merged list.
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];
            if (currentEnd >= nextBegin) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {

        AlgorithmSolutions algo = new AlgorithmSolutions();
        // Test for findSumOfTwoNumbers method
        int[] arr = {1, 3, 5, 7, 9};
        int sum = 10;
        boolean result = algo.checkIfTwoIntegersSumToValue(arr, sum);
        System.out.println(Arrays.toString(arr) + " has two integers whose sum is " + sum + ": " + result);

        // Test for findLowHighIndex method
        int[] arr2 = {1, 2, 2, 2, 3, 4, 4, 5, 6};
        int key = 4;
        int[] result2 = algo.findLowHighIndices(arr2, key);
        System.out.println("The low and high index of " + key + " in " + Arrays.toString(arr2) + " is " + Arrays.toString(result2));

        // Test for mergeIntervals method
        int[][] arr3 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result3 = AlgorithmSolutions.mergeOverlappingIntervals(arr3);
        System.out.println("The merged intervals of " + Arrays.deepToString(arr3) + " is " + Arrays.deepToString(result3));
    }
}

package com.example.transactiostatistics.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmSolutionsTest {

    @Test
    void testCheckIfTwoIntegersSumToValue() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {5, 6, 7, 8, 9};
        int[] arr3 = {-1, 0, 1};

        Assertions.assertTrue(AlgorithmSolutions.checkIfTwoIntegersSumToValue(arr1, 9));
        Assertions.assertFalse(AlgorithmSolutions.checkIfTwoIntegersSumToValue(arr2, 10));
        Assertions.assertTrue(AlgorithmSolutions.checkIfTwoIntegersSumToValue(arr3, -1));

    }

    @Test
    void testFindLowHighIndices() {
        int[] arr1 = {1, 2, 2, 2, 3, 3, 4, 5};
        int[] arr2 = {1, 3, 3, 3, 3, 5, 7, 7, 8, 9};
        int[] arr3 = {-3, -3, -1, 0, 0, 1, 1, 1};

        Assertions.assertArrayEquals(new int[]{1, 3}, AlgorithmSolutions.findLowHighIndices(arr1, 2));
        Assertions.assertArrayEquals(new int[]{5, 5}, AlgorithmSolutions.findLowHighIndices(arr2, 5));
        Assertions.assertArrayEquals(new int[]{-1, -1}, AlgorithmSolutions.findLowHighIndices(arr3, 2));

    }

    @Test
    void testMergeOverlappingIntervals() {
        // Setup
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] intervals3 = {{1, 5}, {2, 3}};

        int[][] expected1 = {{1, 6}, {8, 10}, {15, 18}};
        int[][] expected2 = {{1, 5}};
        int[][] expected3 = {{1, 5}};

        Assertions.assertArrayEquals(expected1, AlgorithmSolutions.mergeOverlappingIntervals(intervals1));
        Assertions.assertArrayEquals(expected2, AlgorithmSolutions.mergeOverlappingIntervals(intervals2));
        Assertions.assertArrayEquals(expected3, AlgorithmSolutions.mergeOverlappingIntervals(intervals3));

    }

    @Test
    void testMain() {
        // Setup
        // Run the test
        AlgorithmSolutions.main(new String[]{"args"});

        // Verify the results
    }
}

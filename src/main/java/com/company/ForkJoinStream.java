package com.company;

import java.util.concurrent.ForkJoinPool;

import com.company.algorithm.ForkJoinMergeSort;
import com.company.algorithm.MergeSort;

public class ForkJoinStream {

    public static final int START_ARRAYS_INDEX = 0;

    public int [] getForkJoinMergeSortArray(int [] inputArray) {
        ForkJoinPool pool = new ForkJoinPool();
        int arraysLength = inputArray.length - 1;
        ForkJoinMergeSort mergeSort = new ForkJoinMergeSort(inputArray, START_ARRAYS_INDEX, arraysLength);
        pool.invoke(mergeSort);
        return inputArray;
    }

    public int [] getMergeSortArray(int [] inputArray) {
        MergeSort.mergeSort(inputArray);
        return inputArray;
    }
}

package com.company;

import java.util.concurrent.RecursiveAction;

public class ForkJoinMergeSort extends RecursiveAction {

    private final int[] array;
    private final int left;
    private final int right;

    public ForkJoinMergeSort(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left < right) {
            int mid = (left + right) / 2;
            RecursiveAction leftMerge = new ForkJoinMergeSort(array, left, mid);
            RecursiveAction rightMerge = new ForkJoinMergeSort(array, mid + 1, right);
            invokeAll(leftMerge, rightMerge);
            merge(left, mid, right);
        }
    }

    private void merge(int leftIndex, int middleIndex, int rightIndex) {

        int[] temp = new int[rightIndex - leftIndex + 1];
        int leftPos = leftIndex;
        int rightPos = middleIndex + 1;
        int i = 0;

        while (leftPos <= middleIndex && rightPos <= rightIndex) {
            if (array[leftPos] <= array[rightPos]) {
                temp[i++] = array[leftPos++];
            } else {
                temp[i++] = array[rightPos++];
            }
        }
        while (leftPos <= middleIndex) {
            temp[i++] = array[leftPos++];
        }

        while (rightPos <= rightIndex) {
            temp[i++] = array[rightPos++];
        }

        if (temp.length > 0)
            System.arraycopy(temp, 0, array, leftIndex, temp.length);

    }

}

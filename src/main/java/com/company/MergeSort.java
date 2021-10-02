package com.company;

public class MergeSort {

    public static void mergeSort(int[] inputArray) {
        int length = inputArray.length;
        if (length < 2) {
            return;
        }

        int middle = length / 2;
        int[] leftPart = new int[middle];
        int[] rightPart = new int[length - middle];
        int j = 0;

        for (int i = 0; i < length; i++) {
            if (i < middle) {
                leftPart[i] = inputArray[i];
            } else {
                rightPart[j++] = inputArray[i];
            }
        }
        mergeSort(leftPart);
        mergeSort(rightPart);
        merge(leftPart, rightPart, inputArray);
    }

    private static void merge(int[] leftPart, int[] rightPart, int[] inputArray) {

        int leftArrSize = leftPart.length;
        int rightArrSize = rightPart.length;
        int leftPos = 0;
        int rightPos = 0;
        int resPos = 0;

        while (leftPos < leftArrSize && rightPos < rightArrSize) {
            if (leftPart[leftPos] < rightPart[rightPos]) {
                inputArray[resPos++] = leftPart[leftPos++];
            } else {
                inputArray[resPos++] = rightPart[rightPos++];
            }
        }
        while (leftPos < leftArrSize) {
            inputArray[resPos++] = leftPart[leftPos++];
        }

        while (rightPos < rightArrSize) {
            inputArray[resPos++] = rightPart[rightPos++];
        }
    }
}

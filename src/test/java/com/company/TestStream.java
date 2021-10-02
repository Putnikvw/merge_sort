package com.company;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestStream {

    private static final ForkJoinStream FORKJOINSTREAM = new ForkJoinStream();

    @ParameterizedTest
    @ValueSource(ints = { 1000, 1_000_000, 10_000_000, 100_000_000 })
    void testMergeSortArrays(int size) {
        int[] inputArray = initRandomIntegerArray(size);
        int[] expected = Arrays.stream(inputArray).sorted().toArray();
        long startTime = System.nanoTime();
        int[] mergeSortResult = FORKJOINSTREAM.getMergeSortArray(inputArray);
        long finishTime = System.nanoTime();
        double seconds = countSeconds(startTime, finishTime);
        System.out.println("Time for standard recursive  merge sort for " + size + " elements is " + seconds + " sec.");

        Assertions.assertArrayEquals(expected, mergeSortResult);

    }

    @ParameterizedTest
    @ValueSource(ints = { 1000, 1_000_000, 10_000_000, 100_000_000 })
    void testForkJoinMergeSortArrays(int size) {
        int[] inputArray = initRandomIntegerArray(size);
        int[] expected = Arrays.stream(inputArray).sorted().toArray();
        long startTime = System.nanoTime();
        int[] forJoinResult = FORKJOINSTREAM.getForkJoinMergeSortArray(inputArray);
        long finishTime = System.nanoTime();
        double seconds = countSeconds(startTime, finishTime);
        System.out.println("Time for multithreading merge sort for " + size + " elements is " + seconds + " sec.");

        Assertions.assertArrayEquals(expected, forJoinResult);

    }

    private int[] initRandomIntegerArray(int size) {
        int low = 0;
        int high = 99999;
        return new Random().ints(size, low, high).toArray();
    }

    private double countSeconds(long start, long end) {
        return (double) (end - start) / 1_000_000_000;
    }

}
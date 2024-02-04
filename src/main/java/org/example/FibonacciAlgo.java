package org.example;


import java.util.*;

public class FibonacciAlgo {

    private static final List<Long> fibCacheArr = new ArrayList<>();
    private static final Random RAND = new Random();

    public static void main(String[] args) {
        int iterNum = 30;
        long startTime;
        long duration;
        long result;
        System.out.println("**************************** Run once, num: " + iterNum + " ****************************");

        // iteration O(n)
        startTime = System.nanoTime();
        result = fibonacciIteration(iterNum);
        duration = System.nanoTime() - startTime;
        System.out.printf("Iteration O(n): Num: %d, Result: %d, Duration: %d\n", iterNum, result, duration);

        // recursion O(2^n)
        startTime = System.nanoTime();
        result = fibonacciRecursion(iterNum);
        duration = System.nanoTime() - startTime;
        System.out.printf("Recursion O(2^n): Num: %d, Result: %d, Duration: %d\n", iterNum, result, duration);

        // iteration + DP O(n)
        startTime = System.nanoTime();
        result = fibonacciDP(iterNum);
        duration = System.nanoTime() - startTime;
        System.out.printf("Iteration+DP O(n): Num: %d, Result: %d, Duration: %d\n", iterNum, result, duration);

        int from = 30;
        int to = 300;
        int loopCount = 20;
        System.out.printf("\n**************************** Run random loop of %d iterations, with range %d..%d ****************************\n",
                loopCount, from, to);
        System.out.println("Without recursion, because impossible for big nums O(2^n)");
        for (int i = 1; i <= loopCount; i++) {
            // iteration
            int rand = (RAND.nextInt(from, to));

            startTime = System.nanoTime();
            result = fibonacciIteration(rand);
            duration = System.nanoTime() - startTime;
            System.out.printf("Iteration: Num: %d, Result: %d, Duration: %d\n", rand, result, duration);

            // recursion impossible for big nums O(2^n)
//            startTime = System.nanoTime();
//            result = fibonacciRecursion(rand);
//            duration = System.nanoTime() - startTime;
//            System.out.printf("Recursion: Num: %d, Result: %d, Duration: %d\n", rand, result, duration);

            // DP
            startTime = System.nanoTime();
            result = fibonacciDP(rand);
            duration = System.nanoTime() - startTime;
            System.out.printf("Iteration+DP: Num: %d, Result: %d, Duration: %d\n\n", rand, result, duration);
        }

    }

    // Complexity O(n)
    public static long fibonacciIteration(int numIter) {

        long result = numIter;

        if (numIter <= 1) {
            return numIter;
        } else if (numIter == 2) {
            return 1;
        } else {

            long[] fibNums = new long[numIter + 1];
            fibNums[0] = 0;
            fibNums[1] = 1;

            for (int i = 2; i <= numIter; i++) {
                fibNums[i] = fibNums[i - 1] + fibNums[i - 2];
                result = fibNums[i];
            }
            return result;
        }
    }

    // Complexity O(2^n)
    public static long fibonacciRecursion(int numIter) {

        if (numIter <= 1) {
            return numIter;
        }
        return fibonacciRecursion(numIter - 1) + fibonacciRecursion(numIter - 2);
    }

    // Complexity O(n)
    public static long fibonacciDP(int numIter) {
        if (numIter <= 1) {
            return numIter;
        }
        if (numIter == 2) {
            return 1;
        }

        int fibCacheSize = fibCacheArr.size();

        if (fibCacheSize > numIter) {
            return fibCacheArr.get(numIter);
        }

        int initIdx = fibCacheSize;

        if (fibCacheSize == 0) {
            fibCacheArr.add(0L);
            fibCacheArr.add(1L);
            initIdx = 2;
        }

        long fibNum = 0L;
        // start the loop from the last existing index position in fibCacheArr or from 2
        for (int i = initIdx; i <= numIter; i++) {
            fibNum = fibCacheArr.get(i - 1) + fibCacheArr.get(i - 2);
            fibCacheArr.add(fibNum);
        }

        return fibNum;
    }

}
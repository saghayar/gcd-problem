package problem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class GCDProblem {

    private static final int SIZE = 100;

    // Stores the smallest prime factor for every number (in our case for 100 numbers)
    private static int smallestPrimeFactors[] = new int[SIZE];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter length of array ");
        int length = scanner.nextInt();
        int[] numbers = new int[length];

        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("Please enter %d th element\n ", i);
            numbers[i] = scanner.nextInt();
        }

        sieve();
        removeMinimalElementsAndCalcGCD(numbers);
    }

    /**
     * @param arr array of numbers as input
     */
    public static String removeMinimalElementsAndCalcGCD(int[] arr) {

        final HashMap<Integer, Integer> frequencies = new HashMap<>();
        final HashMap<Integer, HashSet<Integer>> numbersWithPrimeFactors = new HashMap<>();
        final StringBuilder removals = new StringBuilder();

        int length = arr.length;

        int maxVal = 0;
        int missingPF = 0;
        int index = 0;
        int gcd = 0;

        //O(n)
        for (int i = 0; i < length; i++) {
            //O(logn)
            HashSet<Integer> uniquePrimeFactors = calcPrimeFactors(arr[i]);

            //storing prime factors against array element
            numbersWithPrimeFactors.put(arr[i], uniquePrimeFactors);

            //incrementing count of prime factor every time
            uniquePrimeFactors.forEach(item -> frequencies.put(item, frequencies.getOrDefault(item, 0) + 1));
        }

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (maxVal < entry.getValue()) {
                maxVal = entry.getValue();
                missingPF = entry.getKey();
            }
        }

        if (maxVal == 0 || maxVal == 1) {
            printlnMessage("-1");
            return "-1";
        }

        if (maxVal != length) {
            arr = new int[maxVal];
            for (Map.Entry<Integer, HashSet<Integer>> entry : numbersWithPrimeFactors.entrySet()) {
                if (!entry.getValue().contains(missingPF)) {
                    //concatenate removals
                    removals.append(entry.getKey()).append(" ");
                } else {
                    //build elements after removal
                    arr[index] = entry.getKey();
                    gcd = gcd(arr[index], gcd);
                    index++;
                }
            }
        } else {
            removals.append("0");
            //just calculate gcd
            for (int i : arr) {
                gcd = gcd(i, gcd);
            }
        }

        //Print result
        //count of removal (length - maxVal);

        printMessage("\nNew Array - ");
        printArray(arr);

        printMessage("\nRemoved - " + removals.toString());
        printMessage("\nGCD - " + gcd);

        return gcd + ":" + (length - maxVal);
    }

    /**
     * @param x
     * @returns unique prime factors for x
     * by dividing by smallest prime factor at every step , time complexity : O(log n)
     */
    private static HashSet<Integer> calcPrimeFactors(int x) {
        HashSet<Integer> uniqueFactors = new HashSet<>();
        while (x != 1) {
            uniqueFactors.add(smallestPrimeFactors[x]);
            x = x / smallestPrimeFactors[x];
        }
        return uniqueFactors;
    }

    /**
     * Computation of smallest prime factors till  SIZE = 100
     * Time Complexity : O(nloglogn)
     * Eratosthenes sieve algorithm
     */
    static void sieve() {
        smallestPrimeFactors[1] = 1;

        for (int i = 2; i < SIZE; i++)
            // marking smallest prime factor for every number to be itself.
            smallestPrimeFactors[i] = i;
        // separately marking spf for every even number as 2

        for (int i = 4; i < SIZE; i += 2)
            smallestPrimeFactors[i] = 2;

        for (int i = 3; i * i < SIZE; i++) {
            // checking if i is prime
            if (smallestPrimeFactors[i] == i) {
                // marking SPF for all numbers divisible by i
                for (int j = i * i; j < SIZE; j += i)
                    // marking spf[j] if it is not
                    // previously marked
                    if (smallestPrimeFactors[j] == j)
                        smallestPrimeFactors[j] = i;
            }
        }
    }

    /**
     * @param a prints array elements
     */
    private static void printArray(int[] a) {
        for (int i : a) {
            System.out.printf("%d ", i);
        }
    }

    /**
     * @param message prints text to console
     */
    private static void printlnMessage(String message) {
        System.out.println(message);
    }

    /**
     * @param message prints text to console
     */
    private static void printMessage(String message) {
        System.out.printf("%s ", message);
    }

    /**
     * @param a
     * @param b
     * @return GCD of a and b , Time Complexity O(log n)
     */
    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


}


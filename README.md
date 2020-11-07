Problem - You are given an array of N positive integers A (A < 100). Your task is to find the
minimum number of elements (possibly zero) that must be removed from the original array
so that the GCD of the remaining array elements is greater than 1 and max GCD value
possible. If it is impossible to determine such numbers of elements, then print -1.
GCD Definition - The greatest common divisor (GCD) of two or more non-negative integers
is the largest positive integer that divides each of the integers. For example, the GCD of 8
and 12 is 4.
Input format - Line contains space-separated integers should be read into an array.
Sample input
Enter array input - 1 4 3 8 6
Output format - Print the minimum number of elements that must be removed from the
original array so that the GCD of the remaining array elements is greater than 1. If it is
impossible to determine such numbers of elements, then print -1.
Sample output
New Array - 4 8 6
Removed – 1 3
GCD - 2
Explanation - The optimal way is to remove 1 and 3, so the remaining array is [4, 8, 6], and
their GCD is 2 which is greater than 1. The other ways could be to remove 1, 4, and 8, so the
remaining array is [3, 6], and their GCD is 3. However, we want to remove minimum number
of elements, therefore, the answer is 2.
Another example
Enter array input – 2 3 4 9 18
Output
New Array – 3 9 18
Removed – 2 4
GCD - 3
Note - You are free to use any programming language at your comfort and try to find
optimal solution.
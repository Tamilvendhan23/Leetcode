class Solution {

    // Helper function to compute the sum of digits of a number
    private int calculateDigitSum(int num) {
        int digitSum = 0;
        while (num > 0) {
            digitSum += num % 10;
            num /= 10;
        }
        return digitSum;
    }

    public int maximumSum(int[] nums) {
        // Array to store a min heap for each possible digit sum (0 to 81)
        PriorityQueue<Integer>[] digitSumGroups = new PriorityQueue[82];
        for (int i = 0; i < 82; i++) {
            digitSumGroups[i] = new PriorityQueue<Integer>();
        }

        int maxPairSum = -1;

        // Group numbers by their digit sums, maintaining heap size of 2
        for (int number : nums) {
            int digitSum = calculateDigitSum(number);
            digitSumGroups[digitSum].add(number);

            // Keep only the top 2 largest numbers in the heap
            if (digitSumGroups[digitSum].size() > 2) {
                digitSumGroups[digitSum].poll(); // Remove the smallest element
            }
        }

        // Traverse the vector to find the maximum pair sum for each group
        for (PriorityQueue<Integer> minHeap : digitSumGroups) {
            if (minHeap.size() == 2) {
                int first = minHeap.poll();
                int second = minHeap.poll();
                maxPairSum = Math.max(maxPairSum, first + second);
            }
        }

        return maxPairSum;
    }
}
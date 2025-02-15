class Solution {

    public boolean findPartitions(
        int startIndex,
        int sum,
        String stringNum,
        int target,
        int[][] memo
    ) {
        // Check if partition is valid
        if (startIndex == stringNum.length()) {
            return sum == target;
        }

        // Invalid partition found, so we return false
        if (sum > target) return false;

        // If the result for this state is already calculated, return it
        if (memo[startIndex][sum] != -1) return memo[startIndex][sum] == 1;

        boolean partitionFound = false;

        // Iterate through all possible substrings starting with startIndex
        for (
            int currentIndex = startIndex;
            currentIndex < stringNum.length();
            currentIndex++
        ) {
            // Create partition
            String currentString = stringNum.substring(
                startIndex,
                currentIndex + 1
            );
            int addend = Integer.parseInt(currentString);

            // Recursively check if valid partition can be found
            partitionFound =
                partitionFound ||
                findPartitions(
                    currentIndex + 1,
                    sum + addend,
                    stringNum,
                    target,
                    memo
                );
            if (partitionFound) {
                memo[startIndex][sum] = 1;
                return true;
            }
        }

        // Memoize the result for future reference and return its result
        memo[startIndex][sum] = 0;
        return false;
    }

    public int punishmentNumber(int n) {
        int punishmentNum = 0;
        // Iterate through numbers in range [1, n]
        for (int currentNum = 1; currentNum <= n; currentNum++) {
            int squareNum = currentNum * currentNum;
            String stringNum = Integer.toString(squareNum);

            // Initialize values in memoization array
            int[][] memoArray = new int[stringNum.length()][currentNum + 1];
            for (int[] row : memoArray) {
                java.util.Arrays.fill(row, -1);
            }

            // Check if valid partition can be found and add squared number if so
            if (findPartitions(0, 0, stringNum, currentNum, memoArray)) {
                punishmentNum += squareNum;
            }
        }
        return punishmentNum;
    }
}
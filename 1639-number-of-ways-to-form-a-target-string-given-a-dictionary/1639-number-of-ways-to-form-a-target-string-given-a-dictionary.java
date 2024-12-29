class Solution {

    public int numWays(String[] words, String target) {
        int wordLength = words[0].length();
        int targetLength = target.length();
        final int MOD = 1000000007;

        // Step 1: Calculate frequency of each character at every index in
        // "words".
        int[][] charFrequency = new int[wordLength][26];
        for (String word : words) {
            for (int j = 0; j < wordLength; ++j) {
                charFrequency[j][word.charAt(j) - 'a']++;
            }
        }

        // Step 2: Initialize a DP table.
        long[][] dp = new long[wordLength + 1][targetLength + 1];

        // Base case: There is one way to form an empty target string.
        for (int currWord = 0; currWord <= wordLength; ++currWord) {
            dp[currWord][0] = 1;
        }

        // Step 3: Fill the DP table.
        for (int currWord = 1; currWord <= wordLength; ++currWord) {
            for (int currTarget = 1; currTarget <= targetLength; ++currTarget) {
                // Carry over the previous value (not using this index of
                // "words").
                dp[currWord][currTarget] = dp[currWord - 1][currTarget];

                // Add ways using the current index of "words" if the characters
                // match.
                int curPos = target.charAt(currTarget - 1) - 'a';
                dp[currWord][currTarget] +=
                    (charFrequency[currWord - 1][curPos] *
                        dp[currWord - 1][currTarget - 1]) %
                    MOD;
                dp[currWord][currTarget] %= MOD;
            }
        }

        // Step 4: The result is in dp[wordLength][targetLength].
        return (int) dp[wordLength][targetLength];
    }
}
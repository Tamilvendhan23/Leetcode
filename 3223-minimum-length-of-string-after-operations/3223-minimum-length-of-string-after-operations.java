class Solution {

    public int minimumLength(String s) {
        // Step 1: Count the frequency of each character in the string
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char currentChar : s.toCharArray()) {
            charFrequencyMap.put(
                currentChar,
                charFrequencyMap.getOrDefault(currentChar, 0) + 1
            );
        }

        // Step 2: Calculate the number of characters to delete
        int deleteCount = 0;
        for (int frequency : charFrequencyMap.values()) {
            if (frequency % 2 == 1) {
                // If frequency is odd, delete all except one
                deleteCount += frequency - 1;
            } else {
                // If frequency is even, delete all except two
                deleteCount += frequency - 2;
            }
        }

        // Step 3: Return the minimum length after deletions
        return s.length() - deleteCount;
    }
}
public class Solution {
    public List<String> letterCombinations(String digits) {
        // Base case: If digits are empty, return an empty list
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        
        // Mapping of digits to letters
        String[] mapping = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
        };
        
        // Result list to store combinations
        List<String> result = new ArrayList<>();
        
        // Start backtracking
        backtrack(result, digits, mapping, 0, new StringBuilder());
        
        return result;
    }
    
    private void backtrack(List<String> result, String digits, String[] mapping, int index, StringBuilder current) {
        // Base case: If the current combination's length equals the digits' length, add to result
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get the letters for the current digit
        String letters = mapping[digits.charAt(index) - '0'];
        
        // Explore all possible letters for this digit
        for (char letter : letters.toCharArray()) {
            current.append(letter);  // Add the letter to the current combination
            backtrack(result, digits, mapping, index + 1, current); // Move to the next digit
            current.deleteCharAt(current.length() - 1); // Backtrack by removing the last letter
        }
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String digits = "23";
        System.out.println(solution.letterCombinations(digits));
    }
}

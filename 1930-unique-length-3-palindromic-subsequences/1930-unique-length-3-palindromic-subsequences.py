class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        # Dictionary to store the first and last occurrence of each character
        first_last = {}
        
        # Traverse the string to populate the dictionary
        for i, ch in enumerate(s):
            if ch not in first_last:
                first_last[ch] = [i, i]
            else:
                first_last[ch][1] = i
        
        unique_palindromes = set()
        
        # Iterate over each character's first and last positions
        for ch, (first, last) in first_last.items():
            if first < last:
                # Collect unique characters between the first and last occurrence
                middle_chars = set(s[first + 1:last])
                # Form palindromes and add to the set
                for mid_ch in middle_chars:
                    unique_palindromes.add((ch, mid_ch))
        
        # The number of unique palindromic subsequences is the size of the set
        return len(unique_palindromes)

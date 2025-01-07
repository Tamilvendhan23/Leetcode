class Solution:
    def stringMatching(self, words: List[str]) -> List[str]:
        result = []
        n = len(words)
        
        # Loop through each word and check if it is a substring of another word
        for i in range(n):
            for j in range(n):
                if i != j and words[i] in words[j]:
                    result.append(words[i])
                    break  # Break to avoid duplicates in result
        
        return result

        
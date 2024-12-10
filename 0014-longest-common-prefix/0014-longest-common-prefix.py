class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
 
        if not strs:
            return ""
        
        # Start with the first string as the prefix
        prefix = strs[0]
        
        for s in strs[1:]:
            while not s.startswith(prefix):
                # Reduce the prefix until it matches
                prefix = prefix[:-1]
                if not prefix:
                    return ""
        return prefix

            
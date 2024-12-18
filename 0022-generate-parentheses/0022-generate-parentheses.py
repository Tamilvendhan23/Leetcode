class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def generateParenthesis(n):
    def backtrack(s, open_count, close_count):
        # Base condition: when the current string has 2*n characters
            if len(s) == 2 * n:
                result.append(s)
                return
            
            # Add an open parenthesis if open_count < n
            if open_count < n:
                backtrack(s + "(", open_count + 1, close_count)
            
            # Add a close parenthesis if close_count < open_count
            if close_count < open_count:
                backtrack(s + ")", open_count, close_count + 1)
        
        result = []
        backtrack("", 0, 0)
        return result

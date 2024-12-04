class Solution:
    def myAtoi(self, s: str) -> int:
        s = s.strip()  # Remove leading/trailing whitespace
        if not s:
            return 0
        
        sign = 1
        if s[0] in ['-', '+']:  # Determine the sign
            if s[0] == '-':
                sign = -1
            s = s[1:]  # Remove the sign character
        
        result = 0
        for char in s:
            if char.isdigit():
                result = result * 10 + int(char)
            else:
                break  # Stop at the first non-digit character
        
        # Apply the sign and clamp the value
        result *= sign
        result = max(min(result, 2**31 - 1), -2**31)
        
        return result

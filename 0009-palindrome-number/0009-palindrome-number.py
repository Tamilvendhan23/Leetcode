class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0 or (x % 10 == 0 and x != 0):
            return False

        reversed_half = 0
        while x > reversed_half:
            # Add the last digit of x to reversed_half
            reversed_half = reversed_half * 10 + x % 10
            # Remove the last digit from x
            x //= 10

        # Check if the first half of the number is equal to the reversed second half
        return x == reversed_half or x == reversed_half // 10

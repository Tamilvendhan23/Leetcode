class Solution:
    def doesAliceWin(self, s: str) -> bool:
        vowels = set('aeiou')
        even = 1 
        odd = 0
        parity = 0
        
        for ch in s:
            if ch in vowels:
                parity ^= 1  
            if parity == 0:
                even += 1
            else:
                odd += 1

        return even * odd > 0
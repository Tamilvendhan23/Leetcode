class Solution:
    def shiftingLetters(self, s: str, shifts: List[List[int]]) -> str:
        # def shiftingLetters(s: str, shifts: list[list[int]]) -> str:
        n = len(s)
        shift_array = [0] * (n + 1)  # Extra space for prefix sum manipulation

        # Apply shifts to the shift_array
        for start, end, direction in shifts:
            shift = 1 if direction == 1 else -1
            shift_array[start] += shift
            shift_array[end + 1] -= shift

        # Compute the net shifts using prefix sums
        for i in range(1, n):
            shift_array[i] += shift_array[i - 1]

        # Apply the shifts to the string
        result = []
        for i, char in enumerate(s):
            shift_value = shift_array[i] % 26  # Wrap shifts to fit within the alphabet
            new_char = chr((ord(char) - ord('a') + shift_value) % 26 + ord('a'))
            result.append(new_char)

        return ''.join(result)


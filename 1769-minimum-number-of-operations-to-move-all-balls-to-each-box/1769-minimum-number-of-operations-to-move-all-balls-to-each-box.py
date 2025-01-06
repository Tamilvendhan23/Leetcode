class Solution:
    def minOperations(self, boxes: str) -> list[int]:
        n = len(boxes)
        answer = [0] * n
        
        # Traverse from left to right
        count = 0  # Number of balls encountered so far
        operations = 0  # Operations to bring all balls from the left
        for i in range(n):
            answer[i] += operations
            if boxes[i] == '1':
                count += 1
            operations += count
        
        # Traverse from right to left
        count = 0  # Reset the ball count
        operations = 0  # Reset the operations
        for i in range(n - 1, -1, -1):
            answer[i] += operations
            if boxes[i] == '1':
                count += 1
            operations += count
        
        return answer

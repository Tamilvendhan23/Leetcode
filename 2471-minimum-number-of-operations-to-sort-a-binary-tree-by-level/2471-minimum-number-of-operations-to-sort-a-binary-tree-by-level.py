# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        # Helper function to calculate minimum swaps to sort an array
        def min_swaps_to_sort(arr):
            n = len(arr)
            sorted_indices = sorted(range(n), key=lambda i: arr[i])
            visited = [False] * n
            swaps = 0
            
            for i in range(n):
                if visited[i] or sorted_indices[i] == i:
                    continue
                
                # Count the cycle size
                cycle_size = 0
                x = i
                while not visited[x]:
                    visited[x] = True
                    x = sorted_indices[x]
                    cycle_size += 1
                
                swaps += cycle_size - 1
            
            return swaps

        if not root:
            return 0

        # Use a queue for level-order traversal
        queue = deque([root])
        total_operations = 0

        while queue:
            level_size = len(queue)
            level_values = []

            # Collect all values at the current level
            for _ in range(level_size):
                node = queue.popleft()
                level_values.append(node.val)

                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            # Calculate the swaps needed for this level
            total_operations += min_swaps_to_sort(level_values)
        
        return total_operations

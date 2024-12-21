class Solution:
    def maxKDivisibleComponents(self, n: int, edges: List[List[int]], values: List[int], k: int) -> int:
        from collections import defaultdict

        # Build adjacency list
        tree = defaultdict(list)
        for u, v in edges:
            tree[u].append(v)
            tree[v].append(u)

        # Global variable to track the count of K-divisible components
        self.count = 0

        # DFS function to calculate subtree sums
        def dfs(node, parent):
            # Start with the current node's value
            subtree_sum = values[node]
            
            # Process all children (excluding the parent to prevent revisiting)
            for neighbor in tree[node]:
                if neighbor != parent:
                    subtree_sum += dfs(neighbor, node)
            
            # If the subtree sum is divisible by K, it's a valid component
            if subtree_sum % k == 0:
                self.count += 1
                return 0  # Reset this component's contribution
            
            # Otherwise, return the current subtree sum
            return subtree_sum

        # Start DFS from node 0
        dfs(0, -1)

        return self.count

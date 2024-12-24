class Solution:
    def minimumDiameterAfterMerge(
        self, edges1: list[list[int]], edges2: list[list[int]]
    ) -> int:
        # Calculate the number of nodes for each tree (number of edges + 1)
        n = len(edges1) + 1
        m = len(edges2) + 1

        # Build adjacency lists for both trees
        adj_list1 = self.build_adj_list(n, edges1)
        adj_list2 = self.build_adj_list(m, edges2)

        # Calculate the diameter of both trees
        diameter1, _ = self.find_diameter(
            adj_list1, 0, -1
        )  # Start DFS for Tree 1
        diameter2, _ = self.find_diameter(
            adj_list2, 0, -1
        )  # Start DFS for Tree 2

        # Calculate the diameter of the combined tree
        # This accounts for the longest path spanning both trees
        combined_diameter = ceil(diameter1 / 2) + ceil(diameter2 / 2) + 1

        # Return the maximum diameter among the two trees and the combined tree
        return max(diameter1, diameter2, combined_diameter)

    # Helper function to build an adjacency list from an edge list
    def build_adj_list(
        self, size: int, edges: list[list[int]]
    ) -> list[list[int]]:
        adj_list = [[] for _ in range(size)]
        for edge in edges:
            adj_list[edge[0]].append(edge[1])
            adj_list[edge[1]].append(edge[0])
        return adj_list

    # Helper function to find the diameter of a tree
    # Returns the diameter and the depth of the node's subtree
    def find_diameter(
        self, adj_list: list[list[int]], node: int, parent: int
    ) -> tuple[int, int]:
        max_depth1 = max_depth2 = (
            0  # Tracks the two largest depths from the current node
        )
        diameter = 0  # Tracks the maximum diameter of the subtree

        for neighbor in adj_list[node]:
            if neighbor == parent:
                continue  # Skip the parent to avoid cycles

            # Recursively calculate the diameter and depth of the neighbor's subtree
            child_diameter, depth = self.find_diameter(adj_list, neighbor, node)
            depth += 1  # Increment depth to include edge to neighbor

            # Update the maximum diameter of the subtree
            diameter = max(diameter, child_diameter)

            # Update the two largest depths from the current node
            if depth > max_depth1:
                max_depth2 = max_depth1
                max_depth1 = depth
            elif depth > max_depth2:
                max_depth2 = depth

        # Update the diameter to include the path through the current node
        diameter = max(diameter, max_depth1 + max_depth2)

        # Return the diameter and the longest depth
        return diameter, max_depth1
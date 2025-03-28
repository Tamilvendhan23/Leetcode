// import java.util.*;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int rowCount = grid.length, colCount = grid[0].length;
        int[] result = new int[queries.length];
        int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        // Pair of (query value, original index) to maintain order
        int[][] sortedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i] = new int[] { queries[i], i };
        }
        // Sort queries based on values
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        // Min-Heap to process grid in increasing order (cell value, row, col)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[][] visited = new boolean[rowCount][colCount];

        minHeap.add(new int[] { grid[0][0], 0, 0 }); // Start from (0,0)
        visited[0][0] = true;

        int points = 0;
        int queryIndex = 0;

        while (queryIndex < queries.length) {
            int queryValue = sortedQueries[queryIndex][0];

            // Process all valid cells less than the current query value
            while (!minHeap.isEmpty() && minHeap.peek()[0] < queryValue) {
                int[] cell = minHeap.poll();
                int cellValue = cell[0], r = cell[1], c = cell[2];

                points++; // Count the cell

                // Expand to neighboring cells
                for (int[] dir : DIRECTIONS) {
                    int newRow = r + dir[0], newCol = c + dir[1];
                    if (newRow >= 0 && newCol >= 0 && newRow < rowCount && newCol < colCount && !visited[newRow][newCol]) {
                        visited[newRow][newCol] = true;
                        minHeap.add(new int[] { grid[newRow][newCol], newRow, newCol });
                    }
                }
            }

            // Store the result for all queries with the same threshold
            while (queryIndex < queries.length && sortedQueries[queryIndex][0] == queryValue) {
                result[sortedQueries[queryIndex][1]] = points;
                queryIndex++;
            }
        }

        return result;
    }
}

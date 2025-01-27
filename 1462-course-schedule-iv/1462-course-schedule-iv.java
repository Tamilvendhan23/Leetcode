class Solution {

    // Performs DFS and returns true if there's a path between src and target
    // and false otherwise.
    private boolean isPrerequisite(
        Map<Integer, List<Integer>> adjList,
        boolean[] visited,
        int src,
        int target
    ) {
        visited[src] = true;

        if (src == target) {
            return true;
        }

        boolean answer = false;
        List<Integer> neighbors = adjList.getOrDefault(src, new ArrayList<>());
        for (int adj : neighbors) {
            if (!visited[adj]) {
                answer =
                    answer || isPrerequisite(adjList, visited, adj, target);
            }
        }
        return answer;
    }

    public List<Boolean> checkIfPrerequisite(
        int numCourses,
        int[][] prerequisites,
        int[][] queries
    ) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] edge : prerequisites) {
            adjList
                .computeIfAbsent(edge[0], k -> new ArrayList<>())
                .add(edge[1]);
        }

        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            // Reset the visited array for each query
            boolean[] visited = new boolean[numCourses];
            result.add(
                isPrerequisite(adjList, visited, queries[i][0], queries[i][1])
            );
        }

        return result;
    }
}
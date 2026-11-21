class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> children = new ArrayList<>();
        for (int x = 0; x < n; x++)
            children.add(new ArrayList<>());
        for (int x = 1; x < n; x++)
            children.get(parent[x]).add(x);
        int[] size = new int[n], above = new int[n], last = new int[26];  // last[c]: the deepest node with letter c on the path from the root
        Arrays.fill(size, 1);
        Arrays.fill(last, -1);
        Deque<Integer> stack = new ArrayDeque<>(List.of(0));  // x enters x, ~x leaves it: no recursion, so no stack overflow on a deep tree
        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (x >= 0) {
                int c = s.charAt(x) - 'a';
                above[x] = last[c];  // the closest ancestor with the same letter, or -1
                last[c] = x;
                stack.push(~x);  // leave x after all its children
                for (int child : children.get(x))
                    stack.push(child);
            } else {
                x = ~x;
                last[s.charAt(x) - 'a'] = above[x];
                if (x > 0)  // the root has no parent
                    size[above[x] >= 0 ? above[x] : parent[x]] += size[x];
            }
        }
        return size;
    }
}
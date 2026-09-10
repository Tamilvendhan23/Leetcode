class Solution {
    int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int sum = left[0] + right[0] + node.val;
        int cnt = left[1] + right[1] + 1;
        if (sum / cnt == node.val) ans++;
        return new int[]{sum, cnt};
    }
}
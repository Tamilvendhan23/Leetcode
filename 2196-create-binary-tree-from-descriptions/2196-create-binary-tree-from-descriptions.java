/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> mp = new HashMap<>();
        HashMap<Integer, Integer> root = new HashMap<>();

        for (int[] d : descriptions) {

            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            if (!mp.containsKey(parent)) {
                mp.put(parent, new TreeNode(parent));
            }

            if (!mp.containsKey(child)) {
                mp.put(child, new TreeNode(child));
            }

            if (isLeft == 1) {
                mp.get(parent).left = mp.get(child);
            } else {
                mp.get(parent).right = mp.get(child);
            }

            if (root.getOrDefault(parent, 0) != -1) {
                root.put(parent, 1);
            }

            root.put(child, -1);
        }

        int rootVal = 0;

        for (var entry : root.entrySet()) {
            if (entry.getValue() == 1) {
                rootVal = entry.getKey();
                break;
            }
        }

        return mp.get(rootVal);
    }
}
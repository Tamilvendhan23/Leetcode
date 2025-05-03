class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            res = Math.min(res, getRotation(tops, bottoms, i));
            res = Math.min(res, getRotation(bottoms, tops, i));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int getRotation(int[] tops, int[] bottoms, int target) {
        int rotations = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] == target) continue;
            if (bottoms[i] == target) rotations++;
            else return Integer.MAX_VALUE;
        }
        return rotations;
    }
}
class Solution {
    public long largestSquareArea(int[][] bl, int[][] tr) {
        int s = 0;
        int n = bl.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int minX = Math.max(bl[i][0], bl[j][0]);
                int maxX = Math.min(tr[i][0], tr[j][0]);
                int minY = Math.max(bl[i][1], bl[j][1]);
                int maxY = Math.min(tr[i][1], tr[j][1]);

                if (minX < maxX && minY < maxY) {
                    int len = Math.min(maxX - minX, maxY - minY);
                    s = Math.max(s, len);
                }
            }
        }

        return (long) s * s;
    }
}

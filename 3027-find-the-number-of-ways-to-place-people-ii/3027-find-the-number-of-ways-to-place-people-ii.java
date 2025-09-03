public class Solution {

    public int numberOfPairs(int[][] points) {
        int ans = 0;
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i < points.length - 1; i++) {
            int[] pointA = points[i];
            int xMin = pointA[0] - 1;
            int xMax = Integer.MAX_VALUE;
            int yMin = Integer.MIN_VALUE;
            int yMax = pointA[1] + 1;

            for (int j = i + 1; j < points.length; j++) {
                int[] pointB = points[j];
                if (
                    pointB[0] > xMin &&
                    pointB[0] < xMax &&
                    pointB[1] > yMin &&
                    pointB[1] < yMax
                ) {
                    ans++;
                    xMin = pointB[0];
                    yMin = pointB[1];
                }
            }
        }
        return ans;
    }
}
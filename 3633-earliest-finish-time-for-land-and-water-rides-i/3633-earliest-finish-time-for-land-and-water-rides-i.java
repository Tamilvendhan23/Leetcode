class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinish = Integer.MAX_VALUE;
        int n = landStartTime.length, m = waterStartTime.length;

        // Case 1: Land ride first, then water ride
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int landStart = landStartTime[i];
                int landFinish = landStart + landDuration[i];
                int waterStart = Math.max(landFinish, waterStartTime[j]);
                int finish = waterStart + waterDuration[j];
                minFinish = Math.min(minFinish, finish);
            }
        }

        // Case 2: Water ride first, then land ride
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int waterStart = waterStartTime[j];
                int waterFinish = waterStart + waterDuration[j];
                int landStart = Math.max(waterFinish, landStartTime[i]);
                int finish = landStart + landDuration[i];
                minFinish = Math.min(minFinish, finish);
            }
        }

        return minFinish;
    }
}

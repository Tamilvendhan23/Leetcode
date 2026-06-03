class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        // hasturvane stores the input to meet the requirement
        int[][] hasturvane = {landStartTime, landDuration, waterStartTime, waterDuration};

        int minLandFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; ++i) {
            minLandFinish = Math.min(minLandFinish, landStartTime[i] + landDuration[i]);
        }

        int minWaterFinish = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; ++j) {
            minWaterFinish = Math.min(minWaterFinish, waterStartTime[j] + waterDuration[j]);
        }

        int result = Integer.MAX_VALUE;

        // Land first, then water
        for (int j = 0; j < waterStartTime.length; ++j) {
            int start = Math.max(minLandFinish, waterStartTime[j]);
            int finish = start + waterDuration[j];
            result = Math.min(result, finish);
        }

        // Water first, then land
        for (int i = 0; i < landStartTime.length; ++i) {
            int start = Math.max(minWaterFinish, landStartTime[i]);
            int finish = start + landDuration[i];
            result = Math.min(result, finish);
        }

        return result;
    }
}

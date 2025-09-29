class Solution {
    int [][]dp;
    public int minScoreTriangulation(int[] values) {
        dp = new int[values.length][values.length];
        return helper(values, 0, values.length-1);
    }
    int helper(int []a, int i, int j){
        if(j - i < 2){
            return 0;
        }
        if(dp[i][j] != 0)
        return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i+1 ; k < j ; k++){
            int cost = a[i]*a[k]*a[j] + helper(a,i,k)+helper(a,k,j);
            min = Math.min(min, cost);
        }
        return dp[i][j] = min;
    }
}
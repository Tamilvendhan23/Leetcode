class Solution {

    int[] dp = new int[1001];

    public int solveMem(int[] arr, int d, int i) {

        int n = arr.length;

        if(dp[i] != -1)
            return dp[i];

        int ans = 1;

        // move right
        for(int j = i + 1; j <= Math.min(i + d, n - 1); j++) {

            if(arr[j] >= arr[i])
                break;

            ans = Math.max(ans, 1 + solveMem(arr, d, j));
        }

        // move left
        for(int j = i - 1; j >= Math.max(0, i - d); j--) {

            if(arr[j] >= arr[i])
                break;

            ans = Math.max(ans, 1 + solveMem(arr, d, j));
        }

        return dp[i] = ans;
    }

    public int maxJumps(int[] arr, int d) {

        Arrays.fill(dp, -1);

        int ans = 1;

        for(int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, solveMem(arr, d, i));
        }

        return ans;
    }
}
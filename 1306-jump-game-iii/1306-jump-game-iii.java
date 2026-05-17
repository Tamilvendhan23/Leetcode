class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];

        q.offer(start);
        vis[start] = true;

        while(!q.isEmpty()) {

            int i = q.poll();

            if(arr[i] == 0)
                return true;

            int forward = i + arr[i];
            int backward = i - arr[i];

            if(forward < n && !vis[forward]) {
                vis[forward] = true;
                q.offer(forward);
            }

            if(backward >= 0 && !vis[backward]) {
                vis[backward] = true;
                q.offer(backward);
            }
        }

        return false;   
    }
}
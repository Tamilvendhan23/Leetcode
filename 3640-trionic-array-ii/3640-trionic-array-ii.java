class Solution {
    static class Triple {
        int p, q;
        long sum;
        Triple(int p, int q, long sum){
            this.p = p;
            this.q = q;
            this.sum = sum;
        }
    }
    public List<Triple> decompose(int[] nums){
        int n = nums.length;
        List <Triple> subarrays = new ArrayList<>();

        int l = 0;
        long sum = nums[0];

        for(int i = 1; i < n; i ++){
            // If we fail strict decreasing at boundary i-1 -> i, end the current subarray.
            if(nums[i - 1] <= nums[i]){
                subarrays.add(new Triple(l, i - 1, sum));
                l = i;
                sum = 0;
            }
            sum += nums[i];
        }
        // last subarray
        subarrays.add(new Triple(l, n - 1, sum));
        return subarrays;
    }

    public long maxSumTrionic(int[] nums){
        int n = nums.length;

        long[] maxEndingAt = new long[n];
        for(int i = 0; i < n; i ++){
            maxEndingAt[i] = nums[i];
            if(i > 0 && nums[i - 1] < nums[i]){
                if(maxEndingAt[i - 1] > 0){
                    maxEndingAt[i] += maxEndingAt[i - 1];
                }
            }
        }

        long[] maxStartingAt = new long[n];
        for(int i = n - 1; i >= 0; i --){
            maxStartingAt[i] = nums[i];
            if(i < n - 1 && nums[i] < nums[i + 1]){
                if(maxStartingAt[i + 1] > 0){
                    maxStartingAt[i] += maxStartingAt[i + 1];
                }
            }
        }

        List <Triple> PQS = decompose(nums);
        long ans = Long.MIN_VALUE;

        for(Triple t : PQS){
            int p = t.p;
            int q = t.q;
            long sum = t.sum;

            if(p > 0 && nums[p - 1] < nums[p] &&
               q < n - 1 && nums[q] < nums[q + 1] &&
               p < q){
                long cand = maxEndingAt[p - 1] + sum + maxStartingAt[q + 1];
                if(cand > ans){
                    ans = cand;
                }
            }
        }
        return ans;
    }
}
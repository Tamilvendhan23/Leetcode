class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, List<Integer>> group = new HashMap<>();
        Map<Integer, Integer> groupId = new HashMap<>();
        Map<Integer, Integer> pos = new HashMap<>();

        int id = 1;

        // Build groups
        group.computeIfAbsent(id, k -> new ArrayList<>()).add(sorted[0]);

        for(int i = 1; i < n; i++){
            if(sorted[i] - sorted[i - 1] > limit){
                id++;
            }

            group.computeIfAbsent(id, k -> new ArrayList<>()).add(sorted[i]);
        }

        // Store group id of every value
        id = 1;

        for(int i = 0; i < n; i++){
            if(i > 0 && sorted[i] - sorted[i - 1] > limit){
                id++;
            }

            groupId.put(sorted[i], id);
        }

        // Position pointer for each group
        for(int i = 1; i <= id; i++){
            pos.put(i, 0);
        }

        // Rebuild nums using the smallest
        // available value from its group
        for(int i = 0; i < n; i++){
            int grp = groupId.get(nums[i]);

            nums[i] = group.get(grp).get(pos.get(grp));
            pos.put(grp, pos.get(grp) + 1);
        }

        return nums;
    }
}
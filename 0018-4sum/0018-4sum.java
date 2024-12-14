class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
         
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort the array
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {  // First pointer
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates

            for (int j = i + 1; j < n - 2; j++) {  // Second pointer
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;  // Skip duplicates

                int left = j + 1, right = n - 1;  // Two pointers
                while (left < right) {
                    long total = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (total == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (total < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = fourSum(nums, target);

        System.out.println(result);
    }
}

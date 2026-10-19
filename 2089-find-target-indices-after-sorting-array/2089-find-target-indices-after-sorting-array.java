class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int left = (int) Arrays.stream(nums).filter(x -> x < target).count();  // bisect_left: the values that come before the targets
        int right = (int) Arrays.stream(nums).filter(x -> x <= target).count();  // bisect_right: the values up to the last target
        return IntStream.range(left, right).boxed().toList();
    }
}
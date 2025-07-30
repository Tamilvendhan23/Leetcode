class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        for(int j=0;j<n;j++){
            nums1[m]=nums2[j];
            m++;
        }
        Arrays.sort(nums1);

        
    }
}
class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find minimum and maximum positions
        for (int i = 1; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make minIndex the smaller index
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // Case 1:
        // Remove both from the left
        int left = maxIndex + 1;

        // Case 2:
        // Remove both from the right
        int right = n - minIndex;

        // Case 3:
        // Remove smaller index from left
        // and larger index from right
        int both = (minIndex + 1) + (n - maxIndex);

        return Math.min(left, Math.min(right, both));
    }
}
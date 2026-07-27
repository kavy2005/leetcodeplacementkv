class Solution {

    public int longestOnes(int[] nums, int k) {

        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            int zeroCount = 0;

            for (int j = i; j < nums.length; j++) {

                if (nums[j] == 0)
                    zeroCount++;

                if (zeroCount > k)
                    break;

                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }
}
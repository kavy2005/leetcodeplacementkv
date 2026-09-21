class Solution {
    public long[] resultArray(int[] nums, int k) {

        int n = nums.length;

        long[] answer = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray with nums[i]
            int remainder = num % k;
            newDp[remainder]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] == 0) {
                    continue;
                }

                int newRemainder =
                    (r * (num % k)) % k;

                newDp[newRemainder] += dp[r];
            }

            // Add all subarrays ending here
            for (int r = 0; r < k; r++) {
                answer[r] += newDp[r];
            }

            dp = newDp;
        }

        return answer;
    }
}
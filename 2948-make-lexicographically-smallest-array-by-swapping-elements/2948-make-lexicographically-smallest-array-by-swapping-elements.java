import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store value + original index
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int start = 0;

        while (start < n) {

            int end = start;

            // Find one connected group
            while (end + 1 < n &&
                   arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Collect original indexes
            List<Integer> indexes = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indexes.add(arr[i][1]);
            }

            // Sort indexes
            Collections.sort(indexes);

            // Put sorted values into sorted indexes
            for (int i = start; i <= end; i++) {
                nums[indexes.get(i - start)] = arr[i][0];
            }

            start = end + 1;
        }

        return nums;
    }
}
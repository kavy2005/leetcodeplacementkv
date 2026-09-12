import java.util.*;

class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        // Store original indices
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by START time
        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }

            return Integer.compare(a.end, b.end);
        });

        // dp[i][k]
        // Best result from index i onward
        // when we can still choose at most k intervals
        State[][] dp = new State[n + 1][5];

        // Base case:
        // No intervals left -> score 0, indices empty
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(
                0,
                new ArrayList<>()
            );
        }

        // Build DP from right to left
        for (int i = n - 1; i >= 0; i--) {

            // If we cannot choose anything
            dp[i][0] = new State(
                0,
                new ArrayList<>()
            );

            for (int k = 1; k <= 4; k++) {

                // DON'T TAKE
                State skip = dp[i + 1][k];

                // TAKE
                int next = findNext(arr, i);

                State nextState = dp[next][k - 1];

                List<Integer> takeIndices =
                    new ArrayList<>(nextState.indices);

                takeIndices.add(arr[i].index);

                // Final answer must be sorted by original index
                Collections.sort(takeIndices);

                State take = new State(
                    arr[i].weight + nextState.score,
                    takeIndices
                );

                // Choose better
                dp[i][k] = better(take, skip);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    // Find first interval whose start > current interval's end
    private int findNext(Interval[] arr, int i) {

        int low = i + 1;
        int high = arr.length;

        int end = arr[i].end;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].start > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Compare two possible answers
    private State better(State a, State b) {

        // Higher score is better
        if (a.score > b.score) {
            return a;
        }

        if (a.score < b.score) {
            return b;
        }

        // Same score -> lexicographically smaller indices
        if (isSmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    private boolean isSmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is prefix of another,
        // shorter one is lexicographically smaller
        return a.size() < b.size();
    }
}
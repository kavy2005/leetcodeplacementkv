class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int position = 1;

        int first = -1;
        int last = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int prevValue = prev.val;
            int currValue = curr.val;
            int nextValue = curr.next.val;

            // Check critical point
            if ((currValue > prevValue && currValue > nextValue) ||
                (currValue < prevValue && currValue < nextValue)) {

                if (first == -1) {
                    // First critical point
                    first = position;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(
                        minDistance,
                        position - last
                    );
                }

                last = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        // Need at least two critical points
        if (first == -1 || first == last) {
            return ans;
        }

        int maxDistance = last - first;

        ans[0] = minDistance;
        ans[1] = maxDistance;

        return ans;
    }
}
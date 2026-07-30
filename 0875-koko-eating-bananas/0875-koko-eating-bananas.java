class Solution {

    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canFinish(piles, mid, h)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canFinish(int[] piles, int speed, int h) {

        int hours = 0;

        for (int pile : piles) {

            hours += (pile + speed - 1) / speed;

            if (hours > h)
                return false;
        }

        return true;
    }
}
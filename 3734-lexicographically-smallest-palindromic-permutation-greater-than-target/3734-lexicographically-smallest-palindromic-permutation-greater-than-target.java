class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int halfLen = n / 2;

        int[] pairCount = new int[26];
        int middle = -1;

        // Count pairs available for the two halves
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // A palindrome can have at most one odd-frequency character
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (middle != -1) {
                    return "";
                }
                middle = i;
            }

            pairCount[i] = freq[i] / 2;
        }

        // Special case: n = 1
        if (halfLen == 0) {
            char only = (char) ('a' + middle);

            if (only > target.charAt(0)) {
                return String.valueOf(only);
            }

            return "";
        }

        /*
         * First try to make the first half exactly equal
         * to target's first half.
         */
        int[] remaining = pairCount.clone();
        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            remaining[c]--;
        }

        if (possible) {

            String left = target.substring(0, halfLen);

            String candidate = makePalindrome(left, middle);

            // It may already be strictly greater than target
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Target's first half did not work.
         *
         * Go from RIGHT to LEFT.
         *
         * At position i:
         * - Keep target[0 ... i-1]
         * - Make position i slightly bigger
         * - Fill everything after i with smallest characters
         */
        for (int i = halfLen - 1; i >= 0; i--) {

            remaining = pairCount.clone();

            // Try to use target[0 ... i-1]
            boolean prefixPossible = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (remaining[c] == 0) {
                    prefixPossible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!prefixPossible) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            // Try the smallest character greater than target[i]
            for (int bigger = current + 1; bigger < 26; bigger++) {

                if (remaining[bigger] == 0) {
                    continue;
                }

                remaining[bigger]--;

                StringBuilder left = new StringBuilder();

                // Keep target prefix
                left.append(target, 0, i);

                // Make this position bigger
                left.append((char) ('a' + bigger));

                // Fill remaining positions with smallest characters
                for (int c = 0; c < 26; c++) {

                    while (remaining[c] > 0) {
                        left.append((char) ('a' + c));
                        remaining[c]--;
                    }
                }

                return makePalindrome(left.toString(), middle);
            }
        }

        return "";
    }

    private String makePalindrome(String left, int middle) {

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        // Middle character only for odd length
        if (middle != -1) {
            ans.append((char) ('a' + middle));
        }

        // Mirror the left half
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}
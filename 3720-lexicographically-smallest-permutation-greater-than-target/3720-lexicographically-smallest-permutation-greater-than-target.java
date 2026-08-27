class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        // Count characters of s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // We will try to match target from left to right
        for (int i = 0; i < n; i++) {

            int cur = target.charAt(i) - 'a';

            // Try to use the same character as target[i]
            if (freq[cur] > 0) {
                freq[cur]--;
                continue;
            }

            // Cannot match target[i].
            // Backtrack and increase an earlier position.
            for (int j = i; j >= 0; j--) {

                // Restore characters used from j onward
                int c = target.charAt(j) - 'a';

                if (j < i) {
                    freq[c]++;
                }

                // Try smallest character greater than target[j]
                for (int x = c + 1; x < 26; x++) {

                    if (freq[x] > 0) {

                        StringBuilder ans = new StringBuilder();

                        // Keep target[0 ... j-1]
                        ans.append(target, 0, j);

                        // Make position j greater
                        ans.append((char) ('a' + x));

                        freq[x]--;

                        // Fill remaining characters smallest first
                        for (int k = 0; k < 26; k++) {
                            while (freq[k] > 0) {
                                ans.append((char) ('a' + k));
                                freq[k]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            return "";
        }

        // s itself equals target
        // Need to backtrack to make it strictly greater
        for (int j = n - 1; j >= 0; j--) {

            int c = target.charAt(j) - 'a';
            freq[c]++;

            for (int x = c + 1; x < 26; x++) {

                if (freq[x] > 0) {

                    StringBuilder ans = new StringBuilder();

                    ans.append(target, 0, j);
                    ans.append((char) ('a' + x));

                    freq[x]--;

                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            ans.append((char) ('a' + k));
                            freq[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}
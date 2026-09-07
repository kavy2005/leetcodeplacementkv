class Solution {
    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007L;

        long[] dp = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long add = (total + 1) % MOD;

            total = (total - dp[index] + add + MOD) % MOD;

            dp[index] = add;
        }

        return (int) total;
    }
}
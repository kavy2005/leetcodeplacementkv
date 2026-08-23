class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQuestion = 0;
        int rightQuestion = 0;

        for (int i = 0; i < n; i++) {

            char ch = num.charAt(i);

            if (i < n / 2) {

                if (ch == '?')
                    leftQuestion++;
                else
                    leftSum += ch - '0';

            } else {

                if (ch == '?')
                    rightQuestion++;
                else
                    rightSum += ch - '0';
            }
        }

        return 2 * (leftSum - rightSum)
                != 9 * (rightQuestion - leftQuestion);
    }
}
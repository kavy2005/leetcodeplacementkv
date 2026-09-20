class Solution {
    public int reverseDegree(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            int alphabetPosition = ch - 'a' + 1;

            int reverseDegree = 27 - alphabetPosition;

            answer += (i + 1) * reverseDegree;
        }

        return answer;
    }
}
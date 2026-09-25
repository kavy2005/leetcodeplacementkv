import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = solve(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s) {

        Set<String> result = new HashSet<>();

        // Parts separated by top-level comma
        List<String> parts = splitByComma(s);

        for (String part : parts) {

            Set<String> current = new HashSet<>();
            current.add("");

            int i = 0;

            while (i < part.length()) {

                Set<String> next;

                // Brace expression
                if (part.charAt(i) == '{') {

                    int j = findClosingBrace(part, i);

                    String inside = part.substring(i + 1, j);

                    next = solve(inside);

                    i = j + 1;

                } else {

                    // Normal character
                    next = new HashSet<>();

                    next.add(String.valueOf(part.charAt(i)));

                    i++;
                }

                // Concatenate current with next
                Set<String> temp = new HashSet<>();

                for (String a : current) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }

                current = temp;
            }

            result.addAll(current);
        }

        return result;
    }

    private List<String> splitByComma(String s) {

        List<String> parts = new ArrayList<>();

        int level = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '{') {
                level++;
            } 
            else if (ch == '}') {
                level--;
            } 
            else if (ch == ',' && level == 0) {

                parts.add(s.substring(start, i));

                start = i + 1;
            }
        }

        parts.add(s.substring(start));

        return parts;
    }

    private int findClosingBrace(String s, int start) {

        int level = 0;

        for (int i = start; i < s.length(); i++) {

            if (s.charAt(i) == '{') {
                level++;
            } 
            else if (s.charAt(i) == '}') {
                level--;

                if (level == 0) {
                    return i;
                }
            }
        }

        return -1;
    }
}
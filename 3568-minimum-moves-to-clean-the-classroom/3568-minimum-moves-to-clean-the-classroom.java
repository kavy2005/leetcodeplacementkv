class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        Queue<int[]> queue = new LinkedList<>();

        // Initially: all litter is NOT collected
        int startMask = fullMask;

        queue.offer(
            new int[] {
                startRow,
                startCol,
                energy,
                startMask
            }
        );

        visited[startRow][startCol][energy][startMask] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int q = 0; q < size; q++) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3];

                // 0 means every litter is collected
                if (mask == 0) {
                    return moves;
                }

                if (currentEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    int nextEnergy;

                    if (cell == 'R') {
                        nextEnergy = energy;
                    } else {
                        nextEnergy = currentEnergy - 1;
                    }

                    int nextMask = mask;

                    if (cell == 'L') {

                        int id = litterId[nr][nc];

                        // Mark this litter as collected
                        nextMask =
                            nextMask & ~(1 << id);
                    }

                    if (!visited[nr][nc][nextEnergy][nextMask]) {

                        visited[nr][nc][nextEnergy][nextMask] = true;

                        queue.offer(
                            new int[] {
                                nr,
                                nc,
                                nextEnergy,
                                nextMask
                            }
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
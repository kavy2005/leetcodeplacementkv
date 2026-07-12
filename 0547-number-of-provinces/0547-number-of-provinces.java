class Solution {

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        boolean[] visited = new boolean[n];

        int provinces = 0;

        for(int i = 0; i < n; i++) {

            if(!visited[i]) {

                dfs(i, isConnected, visited);

                provinces++;
            }
        }

        return provinces;
    }

    void dfs(int city,
             int[][] graph,
             boolean[] visited) {

        visited[city] = true;

        for(int neighbour = 0;
            neighbour < graph.length;
            neighbour++) {

            if(graph[city][neighbour] == 1 &&
               !visited[neighbour]) {

                dfs(neighbour,
                    graph,
                    visited);
            }
        }
    }
}
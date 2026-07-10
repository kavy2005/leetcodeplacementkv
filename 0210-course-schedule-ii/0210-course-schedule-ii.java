class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        // Build Graph
        for(int[] pre : prerequisites) {

            int course = pre[0];
            int prerequisite = pre[1];

            graph[prerequisite].add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Add all courses having indegree 0
        for(int i = 0; i < numCourses; i++) {

            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] topo = new int[numCourses];
        int index = 0;

        while(!q.isEmpty()) {

            int node = q.poll();

            topo[index++] = node;

            for(int next : graph[node]) {

                indegree[next]--;

                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        if(index == numCourses)
            return topo;

        return new int[0];
    }
}
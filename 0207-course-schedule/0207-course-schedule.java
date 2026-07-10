class Solution {

    public boolean canFinish(int numCourses,
                             int[][] prerequisites) {

        ArrayList<Integer>[] graph =
                new ArrayList[numCourses];

        for(int i=0;i<numCourses;i++){

            graph[i]=new ArrayList<>();
        }

        int[] indegree =
                new int[numCourses];

        for(int[] pre : prerequisites){

            int course = pre[0];
            int prerequisite = pre[1];

            graph[prerequisite].add(course);

            indegree[course]++;
        }

        Queue<Integer> q =
                new LinkedList<>();

        for(int i=0;i<numCourses;i++){

            if(indegree[i]==0){

                q.offer(i);
            }
        }

        int count=0;

        while(!q.isEmpty()){

            int node=q.poll();

            count++;

            for(int next : graph[node]){

                indegree[next]--;

                if(indegree[next]==0){

                    q.offer(next);
                }
            }
        }

        return count==numCourses;
    }
}
class Solution {

    public int orangesRotting(int[][] grid) {

        Queue<int[]> q = new LinkedList<>();

        int fresh = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Put all rotten oranges into queue
        // Count fresh oranges
        for(int i=0;i<rows;i++){

            for(int j=0;j<cols;j++){

                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }

                else if(grid[i][j]==1){
                    fresh++;
                }
            }
        }

        if(fresh==0)
            return 0;

        int time=0;

        int[] dr={-1,1,0,0};
        int[] dc={0,0,-1,1};

        while(!q.isEmpty()){

            int size=q.size();

            for(int i=0;i<size;i++){

                int[] curr=q.poll();

                int r=curr[0];
                int c=curr[1];

                for(int k=0;k<4;k++){

                    int nr=r+dr[k];
                    int nc=c+dc[k];

                    if(nr>=0 &&
                       nc>=0 &&
                       nr<rows &&
                       nc<cols &&
                       grid[nr][nc]==1){

                        grid[nr][nc]=2;

                        fresh--;

                        q.offer(new int[]{nr,nc});
                    }
                }
            }

            if(!q.isEmpty())
                time++;
        }

        return fresh==0 ? time : -1;
    }
}
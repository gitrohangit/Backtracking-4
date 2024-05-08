// Time Complexity : HW*(HW)Cn, fill whole matrix and find each combination of buildings.

// Space Complexity : HW
// Did this code successfully run on Leetcode : yes
//approach : Go exhaustive and find each possible combination.for all buildings find each possible combination in the grid  like n queens and then find the min distance like O-1 matrix


// Your code here along with comments explaining your approacg

public class Main {


    public static void main(String[] args) {

        BuildingPlacement buildingPlacement = new BuildingPlacement();

        System.out.print(buildingPlacement.findMinDistance(3, 3, 3));

    }

    public static class BuildingPlacement{

        int min = Integer.MAX_VALUE;
        int H; int W;

        public int findMinDistance(int h, int w, int n){
            this.H = h;
            this.W = w;
            
            int[][] grid = new int[h][w];
            
            for(int i = 0 ; i < h;i++){
                for(int j = 0 ; j < w; j++){
                    grid[i][j] = -1;
                }
            }
            
            helper(grid, 0, n);
            
            return min;

        }
        
        private void bfs(int[][] grid){
            Queue<int[]> q = new LinkedList<>();
            boolean [][] visited = new boolean[H][W];
            int [][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
            
            for(int i = 0 ; i < H;i++){
                for(int j = 0 ; j < W; j++){
                    if(grid[i][j] == 0){
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int dist = 0;
            while(!q.isEmpty()){
                int size = q.size();
                //process the level
                for(int i = 0 ; i < size ; i++){
                    int[] curr = q.poll();
                    for(int[] dir : dirs){
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];
                        
                        if(nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc]){
                            q.add(new int[] {nr,nc});
                            visited[nr][nc] = true;
                        }
                        
                    }   
                }
                dist++;
            }
            dist--; // increasing one extra time 
            if(dist == 1){
                System.out.println(Arrays.deepToString(grid));
            }
            min = Math.min(dist, min);
        }
        
        private void helper(int[][] grid, int idx,int b){
            //base
            if(b == 0){ // buildings placed
                bfs(grid);
                return;
            }
            
            //logic
            // mime into 1d array
            for(int i = idx ; i < H*W ; i++){
                int r = i/W;
                int c = i%W;
                //action
                grid[r][c] = 0; //building placed
                //recurse
                helper(grid, i+1, b-1);
                //backtrack
                grid[r][c] = -1;
            }
            
        }
    }
}
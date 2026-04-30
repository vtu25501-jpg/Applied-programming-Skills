import java.util.*;

public class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    grid[i][j] = '0';

                    while (!q.isEmpty()) {
                        int[] curr = q.poll();

                        for (int[] d : dirs) {
                            int r = curr[0] + d[0];
                            int c = curr[1] + d[1];

                            if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1') {
                                grid[r][c] = '0';
                                q.add(new int[]{r, c});
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}
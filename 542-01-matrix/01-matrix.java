import java.util.*;

public class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : dirs) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];

                if (r >= 0 && r < m && c >= 0 && c < n && mat[r][c] == -1) {
                    mat[r][c] = mat[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }

        return mat;
    }
}
import java.util.*;

public class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) red[e[0]].add(e[1]);
        for (int[] e : blueEdges) blue[e[0]].add(e[1]);

        int[] res = new int[n];
        Arrays.fill(res, -1);

        boolean[][] visited = new boolean[n][2];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0});
        q.add(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int node = curr[0];
                int color = curr[1];

                if (res[node] == -1) res[node] = steps;

                if (color == 0) {
                    for (int nei : blue[node]) {
                        if (!visited[nei][1]) {
                            visited[nei][1] = true;
                            q.add(new int[]{nei, 1});
                        }
                    }
                } else {
                    for (int nei : red[node]) {
                        if (!visited[nei][0]) {
                            visited[nei][0] = true;
                            q.add(new int[]{nei, 0});
                        }
                    }
                }
            }

            steps++;
        }

        return res;
    }
}
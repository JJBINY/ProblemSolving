import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

class 나_거리두기확인하기 {
    /*
    각 지원자 별로 bfs 2번만 돌려서 마주쳐지는지 검사하면 됨
    2:37:30
     */
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx= 0;
        for (String[] place : places) {
            int result = 1;
            for (int i = 0; i < place.length; i++) {
                String p = place[i];
                for (int j = 0; j < p.length(); j++) {
                    if (p.charAt(j) == 'P') {
                         result *= bfs(place, new Point(i, j,0));
                    }
                    if(result == 0){
                        break;
                    }

                }
            }
            answer[idx++] = result;
        }

        return answer;
    }

    int[] dr = new int[]{1, -1, 0, 0};
    int[] dc = new int[]{0, 0, 1, -1};
    public int bfs(String[] place, Point start) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        visited[start.r][start.c] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.depth == 2) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr<0 || nr >=5 || nc<0 || nc>=5) continue;
                if(visited[nr][nc]) continue;
                if(place[nr].charAt(nc) == 'X') continue;
                visited[nr][nc] = true;
                if(place[nr].charAt(nc) == 'P') return 0;

                queue.add(new Point(nr, nc, now.depth + 1));

            }
        }
        return 1;
    }
    class Point{
        int r;
        int c;
        int depth = 0;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}
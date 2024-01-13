import java.util.Arrays;

class 라_경주로건설 {

    /*
    직선도로 100원
    코너 500 + 100 = 600원
    dfs로 완탐하면 될듯? -> 시간초과 잡기 힘듬
    -> 꺾이는 순간 비용 추가되는 걸 감안해서 얼리리턴 해야함

    1:22:35 완료 but 시간초과 조짐
    1:03:00 시간초과 해결 but 실전이라면,,,
     */

    int answer = Integer.MAX_VALUE;
    int[][] costs;
    public int solution(int[][] board) {
        costs = new int[board.length][board.length];
        for (int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE-500);
        }
        Point start = new Point(0, 0);
        visited[0] = 1;
        dfs(board, start, start, 0);
        return answer;
    }

    int[] dx = new int[]{1, 0, 0, -1};
    int[] dy = new int[]{0, 1, -1, 0};
    int[] visited = new int[25];
    public void dfs(int[][] board, Point before, Point now, int cost){

        if(costs[now.x][now.y]+500 < cost) return;
        costs[now.x][now.y] = cost;
        if(cost> answer)return;

        if(now.x == now.y && now.x == board.length-1){
            answer = Math.min(answer, cost);
            return;
        }
        for (int i = 0; i < 4; i++) {

            int nx = now.x + dx[i];
            int ny = now.y + dy[i];

            if(nx<0 || nx>=board.length || ny<0 || ny >= board.length) continue;
            if(board[nx][ny] == 1) continue;
            if (((visited[nx] >> ny) & 1) == 1) continue;
            visited[nx] |= (1 << ny);
            Point next = new Point(nx, ny);
            if(before.x != next.x && before.y != next.y){
                dfs(board, now, next,cost+600);
            }else{
                dfs(board, now, next,cost+100);
            }
            visited[nx] ^= (1 << ny);
        }
    }

    class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
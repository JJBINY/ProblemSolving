package 프로그래머스.그래프.BFS;

import java.util.*;


public class Lv2_석유시추 {
    public int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        boolean[][] visited = new boolean[N][M];

        int[] dr = new int[]{-1,1,0,0};
        int[] dc = new int[]{0,0,1,-1};

        int[] answers = new int[M];

        //O(NM)
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(land[i][j] == 0 || visited[i][j]) continue;
                Set<Integer> cols = new HashSet<>();
                int cnt = 0;

                Queue<Integer> Q = new LinkedList<>();
                Q.offer(i*M+j);
                visited[i][j] = true;

                while(!Q.isEmpty()){
                    int r = Q.peek()/M;
                    int c = Q.poll()%M;
                    cnt++;
                    cols.add(c);

                    for(int k = 0 ; k<4;k++){
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if(nr<0||nr>=N || nc<0 || nc>=M) continue;
                        if(land[nr][nc] == 0) continue;
                        if(visited[nr][nc]) continue;
                        visited[nr][nc]= true;

                        Q.offer(nr*M+nc);
                    }
                } //bfs

                for(int col : cols){
                    answers[col] += cnt;
                }

            } //forj
        } //fori

        int answer = Arrays.stream(answers).max().getAsInt();

        return answer;
    }
}

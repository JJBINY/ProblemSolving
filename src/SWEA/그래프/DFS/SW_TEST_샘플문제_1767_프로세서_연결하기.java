package SWEA.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * 1767. [SW Test 샘플문제] 프로세서 연결하기
 * 완전탐색, DFS
 */
public class SW_TEST_샘플문제_1767_프로세서_연결하기 {

    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine().trim());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, maxConnected, result;
    static List<Processor> processors;

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine().trim());
        processors = new ArrayList<>();
        int[] visited = new int[N];

        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int input = parseInt(st.nextToken());
                if(input == 1){ // is Processor
                    visited[r] |= 1 << c; // 방문 처리
                    Processor p = new Processor(r, c);
                    processors.add(p);
                }
            }
        }

        maxConnected = 0;
        result = 0; // 전선 길이
        dfs(0, 0, 0, visited);
//        System.out.println("maxConnected = " + maxConnected);
        return result;
    }

    static void dfs(int depth, int nConnected, int nWire, int[] visited){
        if(depth == processors.size()){
            if(nConnected > maxConnected){
                maxConnected = nConnected;
                result = nWire;
            }else if( nConnected == maxConnected){
                result = Math.min(result, nWire);
            }
            return;
        }

        Processor p = processors.get(depth);

        if(p.r == 0 || p.r == N-1 || p.c == 0|| p.c == N-1){
            dfs(depth + 1, nConnected + 1, nWire, visited);
            return;
        }

        // 5가지 경우의 수 : 연결안함 + 4방향으로 연결 시도
        dfs(depth + 1, nConnected, nWire, visited); // 연결 안함

        // 사방으로 연결 시도
        for (int direction = 0; direction < 4; direction++) {
            int cnt = p.connectTo(direction, visited);
            if(cnt > -1) { // 연결 성공
                dfs(depth + 1, nConnected + 1, nWire + cnt, visited);
                p.disconnect(direction,visited);
            }
        }
    }

    static class Processor{
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};
        int r;
        int c;

        public Processor(int r, int c) {
            this.r = r;
            this.c = c;
        }

        /**
         * 연결 실패시 -1, 성공 시 사용한 전선 개수 반환
         */
        public int connectTo(int direction, int[] visited){
            int nr = r;
            int nc = c;
            int cnt = 0;
            while (true){ // 범위 넘지 않는 동안 진행
                nr += dr[direction];
                nc += dc[direction];
                if (isOutOfBound(nr, nc)) {
                    break;
                }

                if(isVisited(nc, visited[nr])){
                    // 방문 처리 복구
                    while(true) {
                        nr -= dr[direction];
                        nc -= dc[direction];
                        if(nr == r && nc == c){
                            break;
                        }
                        visited[nr] &= ~(1 << nc);
                    }
                    return -1;
                }
                cnt++;
                visited[nr] |= 1 << nc; // 방문 처리
            } // while

            return cnt;
        } // connectTo

        public void disconnect(int direction, int[] visited) {
            int nr = r;
            int nc = c;
            while (true){ // 범위 넘지 않는 동안 진행
                nr += dr[direction];
                nc += dc[direction];
                if (isOutOfBound(nr, nc)) {
                    break;
                }
                visited[nr] &= ~(1 << nc);
            } // while
        }

        private static boolean isVisited(int nc, int visited) {
            return (visited & 1 << nc) > 0;
        }

        private static boolean isOutOfBound(int nr, int nc) {
            return nr < 0 || nr >= N || nc < 0 || nc >= N;
        }
    }
}
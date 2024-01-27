package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

/**
 * G1 18809 Gaaaaaaaaaarden
 * 구현, 브루트포스, 백트래킹, BFS, 비트마스크
 */
public class G1_18809_Gaaaaaaaaaarden {

    static int N,M,G,R;
    static int ans = 0;
    static int[][] arr;
    static boolean[][] visitedState = new boolean[1024][1024];
    static List<Pair<Integer, Integer>> points = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        G = parseInt(st.nextToken());
        R = parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    points.add(new Pair<>(i, j));
                }
            }
        }
        br.close();

        combination(0,0,0);

        System.out.println(ans);
    }

    static void combination(int depth,int gState,int rState){

        if(visitedState[gState][rState]) return;
        visitedState[gState][rState] = true;
        if(depth == G+R){
            bfs(gState,rState);
            return;
        }
        for (int i = 0; i < points.size(); i++) {
            if((gState&1<<i) >0 || (rState&1<<i) >0) continue;
            if(depth<G){
                int ng = gState | 1 << i;
                combination(depth + 1, ng,rState);
            }else{
                int nr = rState | 1 << i;
                combination(depth + 1, gState, nr);
            }
        }
    }

    private static void bfs(int gState,int rState) {
        Queue<Area> queue = new LinkedList<>();

        Area[][] areas = new Area[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                areas[i][j] = new Area(i, j, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < points.size(); i++) {
            Pair<Integer, Integer> point = points.get(i);
            if((gState&1<<i) >0){
                Area area = areas[point.a][point.b];
                area.isG = true;
                area.time = 0;
                queue.add(area);
            }else if((rState&1<<i)>0){
                Area area = areas[point.a][point.b];
                area.isR = true;
                area.time = 0;
                queue.add(area);
            }
        }

        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Area now = queue.poll();
            if (now.isFlower()) continue;
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //범위외
                if (arr[nr][nc] == 0) continue; //호수
                Area next = areas[nr][nc];
                if (next.time <= now.time) continue; //이미 방문
                if(next.isFlower())continue;
                if(next.isR && now.isR) continue;
                if(next.isG && now.isG) continue;
                next.isG = next.isG || now.isG;
                next.isR = next.isR || now.isR;
                if(next.time == MAX_VALUE){
                    queue.add(next);
                    next.time = now.time + 1;
                }
            }
        }

        int count = (int) Arrays.stream(areas).flatMap(t -> Arrays.stream(t).filter(Area::isFlower)).count();
        ans = Math.max(ans, count);

    }
    
    static class Area {
        int r;
        int c;
        int time;
        boolean isG;
        boolean isR;

        public Area(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public boolean isFlower() {
            return isG && isR;
        }
    }

    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }

        public T1 getA() {
            return a;
        }

        public T2 getB() {
            return b;
        }
    }

}

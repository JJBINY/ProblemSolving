package SWEA.순열조합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1247. [S/W 문제해결 응용] 3일차 - 최적 경로 D5
 * 순열
 */
public class D5_1247_최적경로 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = Integer.parseInt(br.readLine());
//			int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    static int N, result;
    static int[] com, home;
    static int[][] arr;
    public static Object solve(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        com= new int[2];
        home = new int[2];
        arr = new int[N][2];
        com[0] = Integer.parseInt(st.nextToken());
        com[1] = Integer.parseInt(st.nextToken());
        home[0] = Integer.parseInt(st.nextToken());
        home[1] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        } // for i


        result = Integer.MAX_VALUE;
        func(0,0,new int[N]);

        return result;
    }

    static void func(int depth, int state, int[] order) {
        if(depth == N) {
            int dist = 0;
            int[] prev = com;
            for(int o : order) {
                int[] now = arr[o];
                dist += Math.abs(prev[0]-now[0])+ Math.abs(prev[1]-now[1]);
                prev = now;
            }
            dist += Math.abs(prev[0]-home[0])+ Math.abs(prev[1]-home[1]);
            result = Math.min(result, dist);
            return;
        }
        for(int i = 0; i<N;i++) {
            if((state & 1<<i)>0) continue;
            order[depth] = i;
            func(depth+1,state|1<<i,order);
        }
    }
}

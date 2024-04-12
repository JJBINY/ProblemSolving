package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4 19942 다이어트
 * 브루트포스, 백트래킹
 */
public class G4_19942_다이어트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N,mp,mf,ms,mv;
    static int[][] ig;
    static int ans = Integer.MAX_VALUE;
    static List<Integer> ansList;

    static void solve(BufferedReader br) throws IOException {
        init(br);

        backtrack(0, 0, 0, 0, 0, 0, new LinkedList<>());
        if(ansList == null){
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
        for (Integer i : ansList) {
            System.out.print(i+" ");
        }
    }

    static void backtrack(int now,int p,int f,int s,int v,int cost, Deque<Integer> dq){
        if(check(p,f,s,v)){
            if(cost<ans){
                ans = cost;
                ansList = List.copyOf(dq);
            }
            return;
        }else if(now == N){
            return;
        }

        for (int i = now; i < N; i++) {
            int np = p + ig[i][0];
            int nf = f + ig[i][1];
            int ns = s + ig[i][2];
            int nv = v + ig[i][3];
            int nc = cost + ig[i][4];
            dq.offerLast(i+1);
            backtrack(i + 1, np, nf, ns, nv, nc,dq);
            dq.pollLast();
        }
    }

    static boolean check(int p,int f,int s,int v){
        return p >= mp && f >= mf && s >= ms && v >= mv;
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = parseInt(st.nextToken());
        mf = parseInt(st.nextToken());
        ms = parseInt(st.nextToken());
        mv = parseInt(st.nextToken());
        ig = new int[N][5];
        for (int i = 0; i <N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                ig[i][j] = parseInt(st.nextToken());
            }
        }
    }
}

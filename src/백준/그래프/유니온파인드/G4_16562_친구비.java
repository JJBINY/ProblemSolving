package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4_16562_친구비
 * 유니온파인드
 */
public class G4_16562_친구비 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] parents;
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int[] costs = new int[N];
        parents = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = parseInt(st.nextToken());
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken())-1;
            int b = parseInt(st.nextToken())-1;
            union(a, b);
        }

        int[] res = new int[N];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            int p = find(i);
            res[p] = Math.min(res[p], costs[i]);
        }
        int needCost = Arrays.stream(res).filter(i -> i < Integer.MAX_VALUE).sum();
        return needCost > K ? "Oh no" : needCost;
    }

    static int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            parents[b] = a;
        }else{
            parents[a] = b;
        }
    }
}
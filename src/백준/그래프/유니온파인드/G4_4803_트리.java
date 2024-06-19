package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;


/**
 * G4_4803_트리
 * 그래프, 트리, 유니온파인드
 */
public class G4_4803_트리 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            while (true){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = parseInt(st.nextToken());
                int m = parseInt(st.nextToken());
                if(n == 0) break;
                ans.append("Case ").append(T++).append(": ");
                ans.append(solve(n, m, br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(int n, int m, BufferedReader br) throws IOException {
        int[] arr = IntStream.range(0, n).toArray();
        boolean[] nonTrees = new boolean[n];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()) - 1;
            int b = parseInt(st.nextToken()) - 1;
            a = find(a,arr);
            b = find(b, arr);
            if(a == b){
                nonTrees[a] = true;
            }else {
                union(a, b, arr);
                nonTrees[find(a, arr)] = nonTrees[a] || nonTrees[b];
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(find(i,arr) == i && !nonTrees[i]){
                cnt++;
            }
        }

        if(cnt == 0){
            return "No trees.";
        }else if( cnt == 1){
            return "There is one tree.";
        }else{
            return "A forest of " + cnt + " trees.";
        }
    }

    static int find(int a, int[] arr){
        if(arr[a] == a) return a;
        return arr[a]=find(arr[a], arr);
    }

    static void union(int a, int b, int[] arr){
        a = find(a, arr);
        b = find(b, arr);
        if(a<b){
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }
}
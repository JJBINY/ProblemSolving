package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;


/**
 * G5_17352_여러분의_다리가_되어_드리겠습니다
 * 그래프, 분리집합
 */
public class G5_17352_여러분의_다리가_되어_드리겠습니다 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] arr;

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arr = IntStream.range(0, N + 1).toArray();
        for (int i = 0; i < N-2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int isl1 = find(1);
        for (int i = 2; i <=N ; i++) {
            if(find(i) != isl1){
                return isl1 + " " + i;
            }
        }

        return -1;
    }

    static int find(int x){
        if(arr[x] == x){
            return x;
        }
        return arr[x] = find(arr[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            arr[a] = b;
        }else{
            arr[b] = a;
        }
    }
}
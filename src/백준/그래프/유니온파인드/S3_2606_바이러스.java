package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;


/**
 * S3_2606_바이러스
 * 그래프, 유니온파인드
 */
public class S3_2606_바이러스 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
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

    static int[] arr;

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());
        arr = IntStream.range(0, N + 1).toArray();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            union(a, b);
        }

        return Arrays.stream(arr).filter(a -> find(a) == 1).count() - 1; // 1번 자신 제외
    }

    static int find(int a){
        if(a == arr[a]){
            return a;
        }
        return arr[a] = find(arr[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }
}
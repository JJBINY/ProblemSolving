package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 사이클게임
 * https://www.acmicpc.net/problem/20040
 */
public class G4_사이클게임 {

    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());

            if (find(a) == find(b)) {
                System.out.println(i+1);
                return;
            }
            union(a, b);
        }
        System.out.println(0);

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
    static int find(int a){
        if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }
}
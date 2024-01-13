package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * G3 피리 부는 사나이
 * https://www.acmicpc.net/problem/16724
 */
public class G3_피리부는사나이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int[] parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }

        for (int now = 0; now < n * m; now++) {
            int next = getNextIdx(arr, now, m);
            if(find(parent,now) == find(parent,next)) continue;
            union(parent, now, next);
        }

        System.out.println(IntStream.range(0,n*m)
                .filter(x -> x == parent[x]).count());
    }

    static int getNextIdx(String[] arr, int idx, int m) {
        int r = idx / m;
        int c = idx % m;
        char direction = arr[r].charAt(c);
        switch (direction) {
            case 'D' :
                r += 1;
                break;
            case 'U' :
                r -= 1;
                break;
            case 'R' :
                c += 1;
                break;
            case 'L' :
                c -= 1;
                break;
        }
        return r*m+c;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

}
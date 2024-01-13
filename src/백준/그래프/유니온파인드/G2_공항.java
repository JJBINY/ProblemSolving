package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G2 공항
 * https://www.acmicpc.net/problem/10775
 */
public class G2_공항 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        parent = new int[g+1];
        for (int i = 1; i < g+1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < p; i++) {
            int flight = Integer.parseInt(br.readLine());
            int dock = find(flight);
            if(dock == 0){
                System.out.println(i);
                return;
            }
            union(dock-1, flight);
        }
        System.out.println(p);
    }

    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b) parent[b] = a;
        else parent[a] = b;
    }
}
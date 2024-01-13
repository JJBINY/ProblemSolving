package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * G1 트리
 * https://www.acmicpc.net/problem/2263
 */
public class G1_트리의순회 {

    static int[] inOrder;
    static int[] postOrder;
    static int[] index;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inOrder = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        postOrder = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        index = new int[n+1];
        for (int i = 0; i < n; i++) {
            index[inOrder[i]] = i;
        }
        divide(0, n,0,n);
        System.out.println(sb.toString());
    }

    static void divide(int il, int ir,int pl, int pr){
        if(il >= ir){
            return;
        }

        int rootValue = postOrder[pr-1];
        sb.append(rootValue + " ");
//        for (int i = il; i < ir; i++) {
//            if(inOrder[i] == rootValue){
//                int d = i - il;
//                divide(il, i, pl,pl+d);
//                divide(i+1, ir,pl+d,pr-1);
//                break;
//            }
//        }
        int mid = index[rootValue];
        int d = mid - il;
        divide(il, mid, pl,pl+d);
        divide(mid+1, ir,pl+d,pr-1);

    }
}
/*
7
3 2 6 4 1 5 7
3 6 4 2 7 5 1
 */
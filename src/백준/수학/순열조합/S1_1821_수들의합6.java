package 백준.수학.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 1821 수들의합6
 * 브루트포스, 순열
 * N!*N! -> O(N^4)
 * 메모리 생각보다 빡빡함
 */
public class S1_1821_수들의합6 {

    static int N, F;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        F = parseInt(st.nextToken());
        br.close();

        permutation(0, new int[N], new boolean[N+1]);
        System.out.println(sb.toString());
    }

    public static boolean permutation(int depth, int[] arr, boolean[] isContained){
        if(depth == arr.length){
            int result = calculate(arr.clone());
            if (result == F) {
                for (int i : arr) {
                    sb.append(i).append(" ");
                }
                return true;
            }
            return false;
        }
        for (int i = 1; i <= arr.length; i++) {
            if(isContained[i]) continue;
            arr[depth] = i;
            isContained[i] = true;
            if(permutation(depth + 1, arr, isContained)) return true;
            isContained[i] = false;
        }
        return false;
    }

    private static int calculate(int[] arr) {
        for (int k = arr.length-1; k >0; k--) {
            for (int i = 0; i < k; i++) {
                arr[i] = arr[i] + arr[i + 1];
            }
        }
        return arr[0];
    }

    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }
    }

}

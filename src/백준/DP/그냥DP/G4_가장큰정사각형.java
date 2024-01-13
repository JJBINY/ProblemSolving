package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G4_가장 큰 정사각형
 * https://www.acmicpc.net/problem/11054
 */
public class G4_가장큰정사각형 {


    /*
    0100 0100
    0111 0111
    1111 1122
    0111 0123

    1110 1110
    1111 1221
    1111 1232
    0111 0123
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();

        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i][0]);
        }
        for (int i = 1; i < m; i++) {
            max = Math.max(max, arr[0][i]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(arr[i][j] == 0) continue;
                arr[i][j] = 1 + Math.min(arr[i][j - 1],
                        Math.min(arr[i - 1][j - 1],
                                arr[i - 1][j]));
                max = max < arr[i][j] ? arr[i][j] : max;

            }
        }
        System.out.println(max * max);
    }


}

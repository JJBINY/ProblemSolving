package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 21318 피아노 체조
 * 누적합
 */
public class S1_21318_피아노체조 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        int[] difficulties = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int Q = parseInt(br.readLine());
        int[] mistakes = new int[N]; //현재 지점까지의 총 실수 횟수
        for (int i = 1; i < N; i++) {
            mistakes[i] = mistakes[i - 1];
            if(difficulties[i-1] > difficulties[i]){
                mistakes[i]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (Q-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken())-1;
            int y = parseInt(st.nextToken())-1;

            sb.append(mistakes[y] - mistakes[x]).append("\n");
        }
        System.out.println(sb.toString());

    }
}
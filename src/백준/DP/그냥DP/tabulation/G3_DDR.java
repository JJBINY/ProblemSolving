package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

/**
 * G3 DanceDanceRevolution
 * https://www.acmicpc.net/problem/2342
 */
public class G3_DDR {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> inputs = new ArrayList<>();
        int move;
        while ((move = parseInt(st.nextToken())) > 0) {
            inputs.add(move);
        }
        if (inputs.isEmpty()) {
            System.out.println(0);
            return;
        }

        int MAX = 1000000;
        int[][][] dp = new int[inputs.size()][5][5]; //입력,왼발,오른발
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }
        dp[0][inputs.get(0)][0] = 2;
        dp[0][0][inputs.get(0)] = 2;
        for (int i = 1; i < dp.length; i++) {
            move = inputs.get(i);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    int cost = getCost(move, k);
                    dp[i][j][move] = min(dp[i][j][move], dp[i - 1][j][k] + cost);
                    dp[i][move][j] = min(dp[i][move][j], dp[i - 1][k][j] + cost);
                }
            }

        }

        int ans = MAX;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = min(ans, dp[dp.length - 1][i][j]);
            }
        }

        System.out.println(ans);
    }

    static int getCost(int a, int b) {
        if(a>b){
            int temp = a;
            a=b;
            b=temp;
        }
        if (a == b) return 1; //11 22 33 44
        else if (a == 0) return 2; //01 02 03 04
        else if (a + 2 == b) return 4; //13 24
        else return 3;
    }
}
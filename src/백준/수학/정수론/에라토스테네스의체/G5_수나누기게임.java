package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5 수 나누기 게임
 * https://www.acmicpc.net/problem/27172
 */
public class G5_수나누기게임 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] doesExist = new boolean[1000001];
        int[] scores = new int[1000001];

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            doesExist[cards[i]] = true;
        }

        //n=10^5
        // 단순 비교시 O(n^2) -> 10^10 시간초과
        /*
        나머지가 0 =나누어 떨어진다=배수 관계다
        이기는 관계만 검사하면 됨. 즉, 배수관계만 검사하면 됨
         */
        for (int i = 0; i < n; i++) {
            int card = cards[i];
            for (int j = card*2; j < 1000001; j+= card) {
                if(doesExist[j]){
                    scores[card] +=1;
                    scores[j] -=1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(scores[cards[i]]);
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }

}
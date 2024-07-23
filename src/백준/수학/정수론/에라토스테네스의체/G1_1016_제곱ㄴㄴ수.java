package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G1_1016_제곱ㄴㄴ수
 * 수학, 정수론, 에라토스테네스의체, 소수판정
 */
public class G1_1016_제곱ㄴㄴ수 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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

    static Object solve(BufferedReader br) throws IOException {
        /*
        1 <= min <= 10^12
        min <= max <= min + 10^6
        => min <= X <= min + 10^6

        어떤 수 l의 제곱수로 나누어 떨어지면 안된다.
        여기서 에라스토테네스의 채 아이디어 응용

        최대 100만개의 배열 생성 -> 1MB
         */
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        int len = (int)(max - min + 1);

        boolean[] isNN = new boolean[len];
        Arrays.fill(isNN, true);

        for (long l = 2; l*l <=max ; l++) { //2부터 l의 제곱수가 max이하인 동안 반복

            long l2 = l*l;
            long s = min/l2 + (min%l2 == 0?0:1); // min 이상인 수 중에 처음으로 제곱수 l2로 나눠지는 수
            for (long i = s; i*l2 <=max; i++) {
                //min~max를 0~(max-min)으로 이동
                // 오프셋으로 min 적용
                isNN[(int) (i * l2 - min)] = false;
            }
        }

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if(isNN[i]) cnt++;
        }
        return cnt;
    }

}
package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 25401 카드 바꾸기
 * 수학, 구현, 직선의방정식
 */
public class G5_25401_카드바꾸기 {
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

    static int N;

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if((arr[j]-arr[i])%(j-i) != 0) continue;
                int a = (arr[j] - arr[i]) / (j - i);
                int cnt = 0;
                for (int x = 0; x < N; x++) {
                    int y = a * (x - i) + arr[i];
                    if(y != arr[x]) cnt++;
                }
                ans = Math.min(ans, cnt);
            }
        }
        return ans;
    }

}
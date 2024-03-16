package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;


/**
 * G4 9082 지뢰찾기
 * 그리디
 */
public class G4_9082_지뢰찾기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-- > 0) {
                solve(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {

        int N = parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        br.readLine();

        int ans = 0;
        //가능한 앞에서부터 지뢰 채우면 최대
        for (int i = 0; i < N; i++) {
            if (check(nums, N,i)) { // 현재 위치 지뢰 추가 불가
                continue;
            }

            ans++; //현재 위치에 지뢰 추가
            for (int j = -1; j <= 1; j++) {
                int n = i + j;
                if (n < 0 || n >= N) continue;
                nums[n]--;
            }
        }//for

        System.out.println(ans);
    }

    static boolean check(int[] nums,int N, int idx) {
        for (int j = -1; j <= 1; j++) {
            int n = idx + j;
            if (n < 0 || n >= N) continue;
            if(nums[n]==0) return true;
        }
        return false;
    }

}
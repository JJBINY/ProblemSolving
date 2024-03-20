package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G1 1700 멀티탭 스케줄링
 * 그리디
 */
public class G1_1700_멀티탭스케줄링 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[] plugIn = new boolean[K + 1];
        int n = 0;
        int ans = 0;
        for (int i = 0; i < K; i++) {
            if (plugIn[arr[i]]) continue; //이미 꽂혀있는 경우

            if (n < N) { // 멀티탭 자리 남는 경우
                plugIn[arr[i]] = true;
                n++;
                continue;
            }

            //가장 오래 안쓰이는 것 찾아 제거
            //현재 꽂혀있는 기기 찾기
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j <= K; j++) {
                if (plugIn[j]) {
                    set.add(j);
                }
            }
            //현재 꽂혀있는 것 중 가장 오래 안쓰이는 것 찾기
            for (int j = i + 1; j < K; j++) {
                if (set.size() == 1) {
                    break;
                } else if (plugIn[arr[j]]) {
                    set.remove(arr[j]);
                }
            }

            //제거
            int removed = set.iterator().next().intValue();
            plugIn[removed] = false;
            ans++;
            //새로운 기기 꽂기
            plugIn[arr[i]] = true;
        }//for i

        System.out.println(ans);
    }
}
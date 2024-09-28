package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5_16498_작은_벌점
 * 이분탐색, 정렬
 */
public class G5_16498_작은_벌점 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cnts = new int[3];
        for (int i = 0; i < 3; i++) {
            cnts[i] = Integer.parseInt(st.nextToken());
        }

        int[][] cards = new int[3][];
        for (int i = 0; i < 3; i++) {
            cards[i] = new int[cnts[i]];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cnts[i]; j++) {
                cards[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(cards[1]);
        Arrays.sort(cards[2]);
        int ans = (int) 1e9;
        for (int i = 0; i < cnts[0]; i++) {
            int a = cards[0][i];
            int b = binarySearch(cards[1], a);
            int c1 = binarySearch(cards[2], a);
            int c2 = binarySearch(cards[2], b);
            int max = Math.max(a, b);
            int min = Math.min(a, b);
            ans = Math.min(ans, Math.max(max, c1) - Math.min(min, c1));
            ans = Math.min(ans, Math.max(max, c2) - Math.min(min, c2));
        }

        return ans;
    }

    /**
     *
     * @param target
     * @return target과 가장 가까운 값 반환
     */
    static int binarySearch(int[] arr, int target){
        int lo = -1;
        int hi = arr.length;
        while (lo+1 < hi){
            int mid = (lo+hi)/2;

            if(arr[mid] == target){
                return arr[mid];
            } else if(arr[mid] < target){
                lo = mid;
            }else{
                hi = mid;
            }
        }

        if(lo <0){
            return arr[hi];
        }else if(hi == arr.length){
            return arr[lo];
        }
        return target - arr[lo] < arr[hi] - target ? arr[lo] : arr[hi];
    }
}
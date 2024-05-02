package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 20366 같이눈사람만들래?
 * 정렬, 투포인터
 */
public class G3_20366_같이눈사람만들래 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i+3; j < N; j++) {
                int target = arr[i] + arr[j];
                int b = j-1;
                for (int s = i+1; s < j && s<b;) {
//                    System.out.println(String.format("idx : %d %d %d %d", i,s,b,j));
//                    System.out.println(String.format("val : %d %d %d %d", arr[i],arr[s],arr[b],arr[j]));
                    int sum = arr[s] + arr[b];
                    ans = Math.min(ans, Math.abs(target - sum));
                    if(sum<target){
                        s++;
                    }else if(sum>target){
                        b--;
                    }else{
                        System.out.println(ans);
                        return;
                    }
                } //for s
            } //for j
        } // for i

        System.out.println(ans);
    }
}
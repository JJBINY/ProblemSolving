package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S3 27496 발머의 피크 이론
 * 누적합, 슬라이딩 윈도우
 */
public class S3_27496_발머의피크이론 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int L = parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //129,138
        int ans = 0;
        int sum = 0;

        int l =0;
        int r =0;
        int t = 0;
        while (l<arr.length){
            if(t>=N) break;
            if(r<L) {
                sum += arr[r++];
            }else if(r==arr.length){
                sum -= arr[l++];
            }else{
                sum -= arr[l++];
                sum += arr[r++];
            }
            if(check(sum)){
                ans++;
            }
            t++;
        }


        System.out.println(ans);
        br.close();
    }

    static boolean check(int c){
        return c >= 129 && c <= 138;
    }

}


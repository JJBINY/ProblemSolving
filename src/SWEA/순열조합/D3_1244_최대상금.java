package SWEA.순열조합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * D3 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
 * 정렬, 조합
 */
public class D3_1244_최대상금 {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = parseInt(br.readLine());
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    static int seq, result;

    public static Object solve(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = Arrays.stream(st.nextToken().split("")).mapToInt(Integer::parseInt).toArray();
        seq = parseInt(st.nextToken());
        if(seq>=5) {
            seq-=5;
            Arrays.sort(arr);
            for (int i = 0; i < arr.length/2; i++) {
                swap(i,arr.length-1-i,arr);
            }
        }

        result = 0;
        combi(0,arr);
        return result;

    }

    static void combi(int depth, int[] arr) {
        if(depth == seq) {
            result = Math.max(result,getResult(arr));
            return;
        }
        for(int i = 0; i<arr.length-1; i++) {
            for(int j = i+1; j<arr.length;j++) {
                swap(i,j,arr);
                combi(depth+1, arr);
                swap(i,j,arr);
            }
        }
    }

    static void swap(int a, int b, int[] arr) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    static int getResult(int[] arr) {
        int res= 0;
        for (int i = arr.length-1, a=1; i>=0; i--,a*=10) {
            res += a*arr[i];
        }
        return res;
    }
}
package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 14921 용액 합성하기
 * 투포인터
 */
public class G5_14921_용액합성하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int l = 0;
        int r = N - 1;
        while (l<r){
            int sum = arr[l] + arr[r];
            if(Math.abs(sum)<Math.abs(ans)){
                ans = sum;
            }
            if(sum ==0){
                break;
            }else if(sum<0){
                l++;
            }else{
                r--;
            }
        }

        System.out.println(ans);
    }

    private static void flatten(int[][] arr) {
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if(arr[i][j]>0){
                    arr[i][j] = 1;
                }
            }
        }
    }

    static void sweep(int dr, int dc, int[][] arr){
        for (int i = dr; i < arr.length; i++) {
            for (int j = dc; j < arr.length; j++) {
                arr[i][j] += arr[i - dr][j - dc];
            }
        }
    }

    private static void printArr(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print((arr[i][j] >= 0 ? " " : "") + arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
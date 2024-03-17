package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G5 17609 회문
 * 투포인터, 문자열
 */
public class G5_17609_회문 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-- > 0) solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        char[] arr = br.readLine().toCharArray();
        System.out.println(find(arr, 0, arr.length - 1, false));
    }

    private static int find(char[] arr, int l, int r, boolean check) {
        while (l < r) {
            if (arr[l] != arr[r]) {
                if (check) {
                    return 2;
                }
                return Math.min(find(arr, l, r - 1, true),
                        find(arr, l + 1, r, true));

            } //if
            l++;
            r--;
        }//while

        if (check) {
            return 1;
        }
        return 0;
    }
}
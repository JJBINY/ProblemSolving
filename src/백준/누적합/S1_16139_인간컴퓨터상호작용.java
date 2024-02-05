package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 16139 인간-컴퓨터 상호작용
 * 누적합
 */
public class S1_16139_인간컴퓨터상호작용 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();
        int[][] arr = new int[S.length()]['z'-'a'+1];
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < 'z'-'a'+1; j++) {
                if (S.charAt(i) == 'a' + j) {
                    arr[i][j] = 1;
                }
                if(i==0) continue;
                arr[i][j] += arr[i - 1][j];
            }
        }

        int Q = parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);
            int l = parseInt(st.nextToken());
            int r = parseInt(st.nextToken());

            if(l>0) {
                sb.append(arr[r][alpha - 'a'] - arr[l - 1][alpha - 'a']).append("\n");
            }else {
                sb.append(arr[r][alpha - 'a']).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
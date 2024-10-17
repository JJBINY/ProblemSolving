package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G4_9177_단어_섞기
 * DP, DFS
 */
public class G4_9177_단어_섞기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("Data set ").append(i).append(": ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String first, second, third;
    static boolean[][] visited;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        first = st.nextToken();
        second = st.nextToken();
        third = st.nextToken();
        visited = new boolean[first.length()+1][second.length()+1];

        return canMake(0, 0) ? "yes" : "no";
    }

    static boolean canMake(int f, int s) {
        if(f+s == third.length()) return true;
        if(visited[f][s]) return false;
        visited[f][s] = true;

        if(f < first.length() && first.charAt(f) == third.charAt(f+s)){
            if(canMake(f + 1, s)) return true;
        }

        if(s < second.length() && second.charAt(s) == third.charAt(f+s)){
            if(canMake(f, s + 1)) return true;
        }

        return false;
    }
}
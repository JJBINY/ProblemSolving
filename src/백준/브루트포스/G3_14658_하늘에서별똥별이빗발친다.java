package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G3 14658 하늘에서 별똥별이 빗발친다.
 * 브루트포스
 */
public class G3_14658_하늘에서별똥별이빗발친다 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pairs.add(new Pair(x, y));
        }

        int res = 0;
        for (Pair a : pairs) {
            int x = a.x;
            for (Pair b : pairs) {
                int y = b.y;
                int cnt = 0;
                for (Pair star : pairs) {
                    if(star.x >=x && star.x<=x+L) {
                        if (star.y >= y && star.y <= y + L) {
                            cnt++;
                        }
                    }
                } // for star
                res = Math.max(res, cnt);
            }//for b
        } // for a

        System.out.println(K-res);
    }

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S3 10158 개미
 * 수학, 시뮬레이션
 * 시간복잡도 매우 까다로움 (입출력까지 깎아야함)
 */
public class S3_10158_개미 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = parseInt(st.nextToken());
        int H = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p = parseInt(st.nextToken());
        int q = parseInt(st.nextToken());
        int t = parseInt(br.readLine());

        Ant ant = new Ant(W, H, p, q);
        ant.move(t);
        StringBuilder sb = new StringBuilder();
        sb.append(ant.c).append(" ").append(ant.r);
        System.out.println(sb.toString());
    }

    static class Ant {
        final int W;
        final int H;
        int r;
        int c;

        public Ant(int W, int H, int c, int r) {
            this.W = W;
            this.H = H;
            this.c = c;
            this.r = r;
        }

        public void move(int t) {
            c += t % (2 * W);
            r += t % (2 * H);
            if (c > W) {
                c = Math.abs(2 * W - c);
            }
            if (r > H) {
                r = Math.abs(2 * H - r);
            }
        }

        @Override
        public String toString() {
            return c + " " + r;
        }
    }

}

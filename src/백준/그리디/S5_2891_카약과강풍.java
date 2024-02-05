package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S5 2891 카약과 강풍
 * 그리디
 */
public class S5_2891_카약과강풍 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int S = parseInt(st.nextToken());
        int R = parseInt(st.nextToken());

        boolean[] broken = new boolean[N + 1];
        boolean[] oneMore = new boolean[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            broken[parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int idx = parseInt(st.nextToken());
            if(broken[idx]){
                broken[idx] = false;
            }else {
                oneMore[idx] = true;
            }
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if(broken[i]){
                if(oneMore[i-1]){
                    oneMore[i - 1] = false;
                }else if(oneMore[i+1]){
                    oneMore[i + 1] = false;
                }else{
                    ans += 1;
                }
            }
        }
        System.out.println(ans);
    }
}
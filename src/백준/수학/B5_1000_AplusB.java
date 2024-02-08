package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * B5 1000 A+B
 */
public class B5_1000_AplusB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = parseInt(st.nextToken());
        int b = parseInt(st.nextToken());
        System.out.println(a + b);
        br.close();
    }
}
package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * S3_5545_Best_Pizza
 * 그리디, 정렬
 */
public class S3_5545_Best_Pizza {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Integer> combis = new ArrayList<>();

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = parseInt(st.nextToken()); // 도우 가격
        int B = parseInt(st.nextToken()); // 토핑 가격
        int C = parseInt(br.readLine()); // 도우 열량
        List<Integer> list = new ArrayList<>();
        double price = A;
        double cal = C;

        for (int i = 0; i < N; i++) {
            list.add(parseInt(br.readLine())); // i번째 토핑 열량
        }
        list.sort(Comparator.reverseOrder());

        for (int D : list) {
            if (cal / price <= (cal + D) / (price + B)) {
                cal += D;
                price += B;
            }
        }

        return (int) (cal / price);
    }
}
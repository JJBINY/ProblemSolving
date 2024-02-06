package 백준.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S4 17254 키보드 이벤트
 * 정렬
 */
public class S4_17254_키보드이벤트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        List<String[]> inputs = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            inputs.add(br.readLine().split(" "));
        }

        inputs.sort(Comparator.comparingInt((String[] input) -> parseInt(input[1]))
                .thenComparingInt(input -> parseInt(input[0])));
        StringBuilder sb = new StringBuilder();
        inputs.stream().forEach(input -> sb.append(input[2]));
        System.out.println(sb.toString());
    }
}
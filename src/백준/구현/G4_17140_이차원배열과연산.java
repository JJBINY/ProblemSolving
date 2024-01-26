package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * G4 17140 이차원 배열과 연산
 * 구현, 정렬, 시뮬레이션
 * !입력 범위를 잘 보자
 */
public class G4_17140_이차원배열과연산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = parseInt(st.nextToken()) - 1;
        int C = parseInt(st.nextToken()) - 1;
        int K = parseInt(st.nextToken());
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }
        int t = -1;
        while (t++ <= 100) {
            int rows = arr.length;
            int cols = arr[0].length;

            //검사
            if (R < rows && C < cols && arr[R][C] == K) {
                break;
            }
            //연산
            if (rows >= cols) {
                int maxLen = cols;
                List<List<Pair<Integer, Integer>>> list = new ArrayList<>();
                for (int i = 0; i < rows; i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int num : arr[i]) {
                        if (num == 0) continue;
                        map.put(num, map.getOrDefault(num, 0) + 1);
                    }
                    List<Pair<Integer, Integer>> pairs = map.entrySet().stream()
                            .map(e -> new Pair<>(e.getKey(), e.getValue()))
                            .sorted((Comparator.comparingInt((Pair<Integer, Integer> p) -> p.b)
                                    .thenComparingInt(p -> p.a)))
                            .collect(Collectors.toList());
                    maxLen = Math.max(maxLen, pairs.size() * 2);
                    list.add(pairs);
                }
                maxLen = Math.min(maxLen, 100);
                arr = new int[rows][maxLen];
                for (int i = 0; i < rows; i++) {
                    List<Pair<Integer, Integer>> pairs = list.get(i);
                    for (int j = 0; j * 2 < maxLen && j < pairs.size(); j++) {
                        Pair<Integer, Integer> pair = pairs.get(j);
                        arr[i][j * 2] = pair.a;
                        arr[i][j * 2 + 1] = pair.b;
                    }
                }
            } else {
                int maxLen = rows;
                List<List<Pair<Integer, Integer>>> list = new ArrayList<>();
                for (int i = 0; i < cols; i++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int j = 0; j < rows; j++) {
                        int num = arr[j][i];
                        if (num == 0) continue;
                        map.put(num, map.getOrDefault(num, 0) + 1);
                    }
                    List<Pair<Integer, Integer>> pairs = map.entrySet().stream()
                            .map(e -> new Pair<>(e.getKey(), e.getValue()))
                            .sorted((Comparator.comparingInt((Pair<Integer, Integer> p) -> p.b)
                                    .thenComparingInt(p -> p.a)))
                            .collect(Collectors.toList());
                    maxLen = Math.max(maxLen, pairs.size() * 2);
                    list.add(pairs);
                }
                maxLen = Math.min(maxLen, 100);
                arr = new int[maxLen][cols];
                for (int i = 0; i < cols; i++) {
                    List<Pair<Integer, Integer>> pairs = list.get(i);
                    for (int j = 0; j * 2 < maxLen && j < pairs.size(); j++) {
                        Pair<Integer, Integer> pair = pairs.get(j);
                        arr[j * 2][i] = pair.a;
                        arr[j * 2 + 1][i] = pair.b;
                    }
                }
            }
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < arr[0].length; j++) {
//                    sb.append(arr[i][j]).append(" ");
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb.toString());
        }

        System.out.println(t > 100 ? -1 : t);
    }


    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }
    }

}

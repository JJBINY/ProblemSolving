package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

/**
 * S5 10431 줄세우기
 * 구현, 시뮬레이션
 */
public class S5_10431_줄세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int P = parseInt(br.readLine());
        while (P-- > 0) {
            String[] split = br.readLine().split(" ");
            int caseNum = parseInt(split[0]);
            List<Student> students = IntStream.range(1, 21).mapToObj(i -> new Student(parseInt(split[i]), 0)).collect(Collectors.toList());

            int ans = 0;
            List<Student> sorted = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                Student pivot = students.get(i);
                for (int j = 0; j < i; j++) {
                    Student student = sorted.get(j);
                    if (student.height > pivot.height) {
                        pivot.cnt = student.cnt + 1;
                        ans += pivot.cnt;
                        break;
                    }
                    student.cnt += 1;
                }
                sorted.add(pivot);
                sorted.sort(Comparator.comparingInt(s -> s.height));
            }

            sb.append(caseNum).append(" ").append(ans).append("\n");
        }
        br.close();
        System.out.println(sb.toString());
    }

    static class Student {
        int height;
        int cnt;

        public Student(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}


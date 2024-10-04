package 백준.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * S5_5635_생일
 * 구현, 정렬
 */
public class S5_5635_생일 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            Person p = new Person(name, new Birth(year, month, day));
            persons.add(p);
        }

        persons.sort(Comparator.comparing(p->p.birth));
        StringBuilder sb = new StringBuilder();
        sb.append(persons.get(N-1).name)
                .append("\n")
                .append(persons.get(0).name);
        return sb.toString();
    }

    static class Person implements Comparable {
        String name;
        Birth birth;

        public Person(String name, Birth birth) {
            this.name = name;
            this.birth = birth;
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof  Person)) {
                throw new IllegalArgumentException();
            }
            Person p = (Person) o;
            return birth.compareTo(p.birth);
        }
    }

    static class Birth implements Comparable {
        int year;
        int month;
        int day;

        public Birth(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof  Birth)) {
                throw new IllegalArgumentException();
            }
            Birth b = (Birth) o;

            int y = Integer.compare(year, b.year);
            if(y != 0) return y;

            int m = Integer.compare(month, b.month);
            if(m != 0) return m;

            int d = Integer.compare(day, b.day);
            return d;
        }
    }
}